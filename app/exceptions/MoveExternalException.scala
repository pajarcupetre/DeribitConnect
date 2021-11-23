package exceptions

import models.ErrorDetails

class MoveExternalException(errorDetails: ErrorDetails) extends RuntimeException {

  override def getMessage: String = s"Failure to move external. Please check the following error: ${errorDetails.message} and ${errorDetails.data.reason}"
}
