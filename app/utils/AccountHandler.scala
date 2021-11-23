package utils

import models.{AccountBalanceInfo, CurrencyHolding, DepositDetails, DepositResponse, HistoryTransactions, InternalMoveDoneDetails, InternalMoveResponse, PortfolioResponse, SubAccountPortfolioDetails, WithdrawDoneResponse, WithdrawalDetails, WithdrawalDoneDetails, WithdrawalsResponse}
import play.api.libs.json.Json
import play.api.libs.ws.WSRequest

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait AccountHandler {

  def getBalance(deribitConnection: DeribitConnection, onlyMain: Boolean): Future[AccountBalanceInfo] = {
    val deribitUrl = deribitConnection.getDeribitURL
    val accountPath = s"$deribitUrl/private/get_subaccounts?with_portfolio=true"
    val request: WSRequest = deribitConnection.getWS.url(accountPath)
    val response = request.addHttpHeaders("Authorization" -> s"Bearer ${deribitConnection.getAuthenticationDetails.access_token}").get()
    response.map(
      response => {
        val subAccountsPortfolioDetailsList = Json.parse(Json.stringify(response.json)).as[PortfolioResponse].result
        val mainAccountPortfolio = subAccountsPortfolioDetailsList.filter(_.`type`.equals("main"))
        val requiredSubAccounts = if (onlyMain) {
          mainAccountPortfolio
        } else {
          subAccountsPortfolioDetailsList
        }
        val userName = mainAccountPortfolio.head.username
        computeBalanceInfo(userName, requiredSubAccounts)
      }
    )
  }

  private def computeBalanceInfo(userName: String, requiredSubAccounts: List[SubAccountPortfolioDetails]): AccountBalanceInfo = {
    val balanceInfo: mutable.Map[String, CurrencyHolding] = new mutable.HashMap[String, CurrencyHolding]
    requiredSubAccounts.foreach(
      subAccount => subAccount.portfolio.foreach(
        currencyWithPortfolioDetails => if (balanceInfo.contains(currencyWithPortfolioDetails._1)) {
          val currentBalance = balanceInfo(currencyWithPortfolioDetails._1)
          val newFundsAvailable = currentBalance.totalAmount + currencyWithPortfolioDetails._2.available_funds
          val newFundsReserved = currentBalance.reservedAmount +
            (currencyWithPortfolioDetails._2.available_funds - currencyWithPortfolioDetails._2.available_withdrawal_funds)
          balanceInfo.put(currencyWithPortfolioDetails._1, CurrencyHolding(currencyWithPortfolioDetails._1, newFundsAvailable, newFundsReserved))
        } else {
          balanceInfo.put(currencyWithPortfolioDetails._1,
            CurrencyHolding(currencyWithPortfolioDetails._1, currencyWithPortfolioDetails._2.available_funds,
              currencyWithPortfolioDetails._2.available_funds - currencyWithPortfolioDetails._2.available_withdrawal_funds)
          )
        }
      )
    )
    AccountBalanceInfo(userName, balanceInfo.values.toList)
  }

  def getHistoricDeposit(deribitConnection: DeribitConnection): Future[List[DepositDetails]] = {
    val deribitUrl = deribitConnection.getDeribitURL
    val depositPath = s"$deribitUrl/private/get_deposits?currency="
    val currencyList = Seq("BTC", "ETH", "USDT", "USDC")
    val futures: Seq[Future[List[DepositDetails]]] = currencyList.map(
      currency => {
        val responseOnDeposit = getPathBasedOnCurrency(deribitConnection, depositPath, currency)
          .addHttpHeaders("Authorization" -> s"Bearer ${deribitConnection.getAuthenticationDetails.access_token}").get()
        responseOnDeposit.map(
          response => {
            val depositResponse = Json.parse(Json.stringify(response.json)).as[DepositResponse].result
            depositResponse.data
          }
        )
      }
    )
   Future.reduceLeft(futures)(_ ++ _)

  }

  def getHistoricWithdrawals(deribitConnection: DeribitConnection): Future[List[WithdrawalDetails]] = {
    val deribitUrl = deribitConnection.getDeribitURL
    val depositPath = s"$deribitUrl/private/get_withdrawals?currency="
    val currencyList = Seq("BTC", "ETH", "USDT", "USDC")
    val futures: Seq[Future[List[WithdrawalDetails]]] = currencyList.map(
      currency => {
        val responseOnDeposit = getPathBasedOnCurrency(deribitConnection, depositPath, currency)
          .addHttpHeaders("Authorization" -> s"Bearer ${deribitConnection.getAuthenticationDetails.access_token}").get()
        responseOnDeposit.map(
          response => {
            val withdrawalResponse = Json.parse(Json.stringify(response.json)).as[WithdrawalsResponse].result
            withdrawalResponse.data
          }
        )
      }
    )
    Future.reduceLeft(futures)(_ ++ _)
  }

  def withdrawToExternal(deribitConnection: DeribitConnection, externalAccount: String, amount: Double, currency: String): Future[WithdrawalDoneDetails] = {
    val deribitUrl = deribitConnection.getDeribitURL
    val accountPath = s"$deribitUrl/private/submit_transfer_to_user?amount=$amount&currency=$currency&destination=$externalAccount"
    val request: WSRequest = deribitConnection.getWS.url(accountPath)
    val response = request.addHttpHeaders("Authorization" -> s"Bearer ${deribitConnection.getAuthenticationDetails.access_token}").get()
    response.map(
      withdrawResponse => {
        Json.parse(Json.stringify(withdrawResponse.json)).as[WithdrawDoneResponse].result
      }
    )
  }

  def moveToSubAccount(deribitConnection: DeribitConnection, idSubAccount: Int, amount: Double, currency: String): Future[InternalMoveDoneDetails] = {
    val deribitUrl = deribitConnection.getDeribitURL
    val accountPath = s"$deribitUrl/private/submit_transfer_to_subaccount?amount=$amount&currency=$currency&destination=$idSubAccount"
    val request: WSRequest = deribitConnection.getWS.url(accountPath)
    val response = request.addHttpHeaders("Authorization" -> s"Bearer ${deribitConnection.getAuthenticationDetails.access_token}").get()
    response.map(
      withdrawResponse => {
        Json.parse(Json.stringify(withdrawResponse.json)).as[InternalMoveResponse].result
      }
    )
  }

  private def getPathBasedOnCurrency(deribitConnection: DeribitConnection, initPath: String, currency: String) = {
    deribitConnection.getWS.url(s"$initPath$currency")
  }
}

object AccountHandler extends AccountHandler
