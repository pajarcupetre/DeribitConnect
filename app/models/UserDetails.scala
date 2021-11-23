package models

import play.api.libs.json.{Format, Json}

case class UserDetails(clientId: String, clientSecret:String)

object UserDetails {
  implicit val format: Format[UserDetails] = Json.format
}
