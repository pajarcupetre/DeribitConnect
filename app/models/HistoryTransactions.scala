package models

import play.api.libs.json.{Format, Json}

case class HistoryTransactions(depositDetails: List[DepositDetails], withdrawalDetails: List[WithdrawalDetails])

object HistoryTransactions {
  implicit val format: Format[HistoryTransactions] = Json.format
}