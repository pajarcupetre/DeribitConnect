package models

import play.api.libs.json.{Format, Json}

case class InternalMoveResponse(jsonrpc: String, id: Int, result: InternalMoveDoneDetails)

object InternalMoveResponse {
  implicit val format: Format[InternalMoveResponse] = Json.format
}


