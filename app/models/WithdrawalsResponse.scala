package models

import play.api.libs.json.{Format, Json}

case class WithdrawalsResponse(jsonrpc: String, result: WithdrawalListDetails, usIn: BigInt, usOut: BigInt, usDiff: BigInt, testnet: Boolean)

object WithdrawalsResponse {
  implicit val format: Format[WithdrawalsResponse] = Json.format
}


