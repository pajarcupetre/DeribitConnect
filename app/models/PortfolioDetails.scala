package models

import play.api.libs.json.{Format, Json}

case class PortfolioDetails(margin_balance: Double,
                            maintenance_margin: Double,
                            initial_margin: Double,
                            equity: Double,
                            currency: String,
                            balance: Double,
                            available_withdrawal_funds: Double,
                            available_funds: Double)

object PortfolioDetails {
  implicit val format: Format[PortfolioDetails] = Json.format
}