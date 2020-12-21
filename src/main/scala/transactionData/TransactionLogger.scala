package transactionData

import cloudflow.akkastream.scaladsl.{FlowWithCommittableContext, RunnableGraphStreamletLogic}
import cloudflow.akkastream.{AkkaStreamlet}
import cloudflow.streamlets.StreamletShape
import cloudflow.streamlets.avro.AvroInlet

class TransactionLogger extends AkkaStreamlet {

  val in = AvroInlet[TransactionModel]("in")

  override def shape(): StreamletShape = StreamletShape.withInlets(in)

  override protected def createLogic() = new RunnableGraphStreamletLogic() {

    def flow =
      FlowWithCommittableContext[TransactionModel]
        .mapConcat { data =>
          List(
            println("LogData: " +  data.name + " - " + data.value)
          )
        }

    def runnableGraph = sourceWithCommittableContext(in).via(flow).to(committableSink)
  }
}
