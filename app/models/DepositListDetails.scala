package models

import play.api.libs.json.{Format, Json}

case class DepositListDetails(data: List[DepositDetails], count: Int)

object DepositListDetails {
  implicit val format: Format[DepositListDetails] = Json.format
}