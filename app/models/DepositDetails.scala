package models

import play.api.libs.json.{Format, Json}


case class DepositDetails(updated_timestamp: BigInt,
                          transaction_id: String,
                          state: String,
                          received_timestamp: Long,
                          note: String,
                          currency: String,
                          amount: Double,
                          address: String)

object DepositDetails {
  implicit val format: Format[DepositDetails] = Json.format
}
