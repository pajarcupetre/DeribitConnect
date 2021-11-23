package models

import play.api.libs.json.{Format, Json}

case class WithdrawalListDetails(data: List[WithdrawalDetails], count: Int)

object WithdrawalListDetails {
  implicit val format: Format[WithdrawalListDetails] = Json.format
}