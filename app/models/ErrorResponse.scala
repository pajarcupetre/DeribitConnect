package models

import play.api.libs.json.{Format, Json}

case class ErrorResponse(jsonrpc: String, error: ErrorDetails, usIn: BigInt, usOut: BigInt, usDiff: BigInt, testnet: Boolean)

object ErrorResponse {
  implicit val format: Format[ErrorResponse] = Json.format
}



