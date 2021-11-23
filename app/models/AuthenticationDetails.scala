package models

import play.api.libs.json.{Format, Json}

case class AuthenticationDetails(token_type: String, scope: String, refresh_token: String, expires_in: Int,  access_token: String)

object AuthenticationDetails {
  implicit val format: Format[AuthenticationDetails] = Json.format
}
