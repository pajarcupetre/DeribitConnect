package models

import play.api.libs.json.{Format, Json}

case class WithdrawalDetails(address: String,
                             amount: Double,
                             confirmed_timestamp: BigInt,
                             created_timestamp: BigInt,
                             currency: String,
                             fee: Double,
                             id: Int,
                             priority: Double,
                             state: String,
                             transaction_id: String,
                             updated_timestamp: BigInt)

object WithdrawalDetails {
  implicit val format: Format[WithdrawalDetails] = Json.format
}
