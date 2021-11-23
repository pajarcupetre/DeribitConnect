package models

import play.api.libs.json.{Format, Json}

case class AuthenticationResponse(jsonrpc: String, result: AuthenticationDetails, usIn: BigInt, usOut: BigInt, usDiff: BigInt, testnet: Boolean)

object AuthenticationResponse {
  implicit val format: Format[AuthenticationResponse] = Json.format
}
