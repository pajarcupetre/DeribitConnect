package models

import play.api.libs.json.{Format, Json}

case class PortfolioResponse(jsonrpc: String, result: List[SubAccountPortfolioDetails], usIn: BigInt, usOut: BigInt, usDiff: BigInt, testnet: Boolean)

object PortfolioResponse {
  implicit val format: Format[PortfolioResponse] = Json.format
}

