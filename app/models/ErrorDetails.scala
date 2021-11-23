package models

import play.api.libs.json.{Format, Json}

case class ErrorDetails(message: String, data: ErrorDetailsData, code: Int)

object ErrorDetails {
  implicit val format: Format[ErrorDetails] = Json.format
}
