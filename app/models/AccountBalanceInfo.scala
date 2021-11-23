package models

import play.api.libs.json.{Format, Json}

case class AccountBalanceInfo(userName: String, currentBalance:List[CurrencyHolding])

object AccountBalanceInfo {
  implicit val format: Format[AccountBalanceInfo] = Json.format
}
