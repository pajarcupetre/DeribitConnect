package models

import play.api.libs.json.{Format, Json, OFormat}

case class InternalMoveDoneDetails(updated_timestamp: BigInt,
                                   `type`: String,
                                   state: String,
                                   other_side: String,
                                   id: Int,
                                   direction: String,
                                   currency: String,
                                   created_timestamp: BigInt,
                                   amount: Double)

object InternalMoveDoneDetails {
  implicit val format: Format[InternalMoveDoneDetails] = Json.format
}