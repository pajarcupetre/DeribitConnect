package models

import play.api.libs.json.{Format, Json}

case class CurrencyHolding(currencyName: String, totalAmount: Double, reservedAmount: Double)

object CurrencyHolding {
  implicit val format: Format[CurrencyHolding] = Json.format
}
