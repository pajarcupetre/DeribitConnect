package models

import play.api.libs.json.Json.JsValueWrapper
import play.api.libs.json._

case class SubAccountPortfolioDetails(username: String,
                                      `type`: String,
                                      system_name: String,
                                      security_keys_enabled: Boolean,
                                      security_keys_assignments: List[String],
                                      referrals_count: Int,
                                      receive_notifications: Boolean,
                                      portfolio: Map[String, PortfolioDetails],
                                      login_enabled: Boolean,
                                      is_password: Boolean,
                                      id: Long,
                                      email: String
                                     )

object SubAccountPortfolioDetails {

  implicit val format: Format[SubAccountPortfolioDetails] = Json.format
}
