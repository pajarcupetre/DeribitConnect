package models

import play.api.libs.json._

case class WithdrawalDoneDetails(updated_timestamp: BigInt,
                                 `type`: String,
                                 state: String,
                                 other_side: String,
                                 id: Int,
                                 direction: String,
                                 currency: String,
                                 created_timestamp: BigInt,
                                 amount: Double)

object WithdrawalDoneDetails {
  implicit val format: Format[WithdrawalDoneDetails] = Json.format
}