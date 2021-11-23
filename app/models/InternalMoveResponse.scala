package models

import play.api.libs.json.{Format, Json}

case class InternalMoveResponse(jsonrpc: String, result: InternalMoveDoneDetails, usIn: BigInt, usOut: BigInt, usDiff: BigInt, testnet: Boolean)

object InternalMoveResponse {
  implicit val format: Format[InternalMoveResponse] = Json.format
}


