package transactionData

import cloudflow.akkastream._
import cloudflow.akkastream.util.scaladsl._

import cloudflow.streamlets._
import cloudflow.streamlets.avro._

import transactionData.TransactionModelJsonSupport._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

class TransactionHttpIngress extends AkkaServerStreamlet {

  val out = AvroOutlet[TransactionModel]("out").withPartitioner(RoundRobinPartitioner)

  def shape = StreamletShape.withOutlets(out)

  override def createLogic = HttpServerLogic.default(this, out)
}

