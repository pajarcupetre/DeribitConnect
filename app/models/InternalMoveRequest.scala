package models

import play.api.libs.json.{Format, Json}

case class InternalMoveRequest(clientId: String, clientSecret:String, currency: String, amount:Double, idSubAccount:Int)

object InternalMoveRequest {
  implicit val format: Format[InternalMoveRequest] = Json.format
}


