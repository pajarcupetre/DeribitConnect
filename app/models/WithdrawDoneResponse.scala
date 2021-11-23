package models

import play.api.libs.json.{Format, Json}

case class WithdrawDoneResponse(jsonrpc: String, id: Int, result: WithdrawalDoneDetails)

object WithdrawDoneResponse {
  implicit val format: Format[WithdrawDoneResponse] = Json.format
}
