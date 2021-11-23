package models

import play.api.libs.json.{Format, Json}

case class DepositResponse(jsonrpc: String, result: DepositListDetails, usIn: BigInt, usOut: BigInt, usDiff: BigInt, testnet: Boolean)

object DepositResponse {
  implicit val format: Format[DepositResponse] = Json.format
}



