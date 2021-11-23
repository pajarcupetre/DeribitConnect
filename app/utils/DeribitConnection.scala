package utils

import models.{AuthenticationDetails, AuthenticationResponse, UserDetails}
import play.api.Configuration
import play.api.libs.json.Json
import play.api.libs.ws.{WSClient, WSRequest}

import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration.Duration

class DeribitConnection(userDetails: UserDetails, config: Configuration, ws: WSClient) {

  private val deribitUrl = config.get[String]("deribit.url")
  private val AUTH_URL = s"$deribitUrl/public/auth"
  private val authDetails: AuthenticationDetails = Await.result(connect, Duration(120, TimeUnit.SECONDS))

  private def connect: Future[AuthenticationDetails] = {
    val request: WSRequest = ws.url(AUTH_URL)
    val queryParams = Seq(
      ("client_id",userDetails.clientId), ("client_secret", userDetails.clientSecret),("grant_type", "client_credentials")
    )
    val finalRequest = request.addQueryStringParameters(queryParams: _*)
    finalRequest.get().map(
      futureResponse => {
        Json.parse(Json.stringify(futureResponse.json)).as[AuthenticationResponse].result
      }
    )

  }

  def getAuthenticationDetails: AuthenticationDetails = authDetails

  def getDeribitURL: String = deribitUrl

  def getWS: WSClient = ws

}
