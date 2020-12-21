package transactionData

import spray.json.{DefaultJsonProtocol, JsString, JsValue, JsonFormat, deserializationError}

object TransactionModelJsonSupport extends DefaultJsonProtocol {

  implicit val transactionModelFormat = jsonFormat2(TransactionModel.apply)
}
