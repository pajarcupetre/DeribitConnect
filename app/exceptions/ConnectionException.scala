package exceptions

import models.UserDetails

class ConnectionException(userDetails: UserDetails) extends RuntimeException {

  override def getMessage: String = s"Failure to connect using following key: ${userDetails.clientId}"
}
