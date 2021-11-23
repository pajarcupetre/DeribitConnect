package controllers

import models._
import play.api.Configuration
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws._
import play.api.mvc._
import utils.{AccountHandler, DBHandler, DeribitConnection}

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class AccountController @Inject()( ws: WSClient, config: Configuration, val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext)
   extends BaseController {

  def getAccountBalance(clientId: String, clientSecret: String, onlyMain: Boolean): Action[AnyContent] = Action.async {
    val deribitConnection = new DeribitConnection(UserDetails(clientId, clientSecret), config, ws)
    val balanceInfo = AccountHandler.getBalance(deribitConnection, onlyMain)
    balanceInfo.map(
      balance => {
        DBHandler.updateAccountInfo(balance, config)
        Ok(Json.toJson(balance))
      }
    )
  }

  def getHistory(clientId: String, clientSecret: String): Action[AnyContent] = Action.async {
    val deribitConnection = new DeribitConnection(UserDetails(clientId, clientSecret), config, ws)
    val depositsFuture = AccountHandler.getHistoricDeposit(deribitConnection)
    val withdrawalsFuture = AccountHandler.getHistoricWithdrawals(deribitConnection)
    val resultFuture = for {
      depositis <- depositsFuture
      withdrawals <- withdrawalsFuture
    } yield HistoryTransactions(depositis, withdrawals)
    resultFuture.map(
      result => Ok(Json.toJson(result))
    )
  }

  def withdrawCrypto: Action[AnyContent] = Action.async { request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson
    val withdrawDetails = Json.parse(Json.stringify(jsonBody.get)).as[ExternalWithdrawRequest]
    val deribitConnection = new DeribitConnection(UserDetails(withdrawDetails.clientId, withdrawDetails.clientSecret), config, ws)
    val withdrawalsResponseFuture = AccountHandler.withdrawToExternal(deribitConnection, withdrawDetails.externalAccount, withdrawDetails.amount, withdrawDetails.currency)
    withdrawalsResponseFuture.map(
      withdrawDoneResponse => Ok(Json.toJson(withdrawDoneResponse))
    )
  }

  def transferBetweenAccounts(): Action[AnyContent] = Action.async { request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson
    val withdrawDetails = Json.parse(Json.stringify(jsonBody.get)).as[InternalMoveRequest]
    val deribitConnection = new DeribitConnection(UserDetails(withdrawDetails.clientId, withdrawDetails.clientSecret), config, ws)
    val withdrawalsResponseFuture = AccountHandler.moveToSubAccount(deribitConnection, withdrawDetails.idSubAccount, withdrawDetails.amount, withdrawDetails.currency)
    withdrawalsResponseFuture.map(
      withdrawDoneResponse => Ok(Json.toJson(withdrawDoneResponse))
    )
  }



}
