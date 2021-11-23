package models

import play.api.libs.json.{Format, Json}

case class ErrorDetailsData(reason: String, param: String)

object ErrorDetailsData {
  implicit val format: Format[ErrorDetailsData] = Json.format
}
