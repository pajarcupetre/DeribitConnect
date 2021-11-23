package models

import play.api.libs.json.{Format, Json}

case class ExternalWithdrawRequest(clientId: String, clientSecret:String, currency: String, amount:Double, externalAccount:String)

object ExternalWithdrawRequest {
  implicit val format: Format[ExternalWithdrawRequest] = Json.format
}


