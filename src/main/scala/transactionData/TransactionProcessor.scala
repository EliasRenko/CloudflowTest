package transactionData

import akka.stream.scaladsl.RunnableGraph
import cloudflow.akkastream.AkkaStreamlet
import cloudflow.akkastream.scaladsl.{FlowWithCommittableContext, RunnableGraphStreamletLogic}
import cloudflow.akkastream.util.scaladsl.Splitter
import cloudflow.streamlets.avro.{AvroInlet, AvroOutlet}
import cloudflow.streamlets.{RoundRobinPartitioner, StreamletShape}

class TransactionProcessor extends AkkaStreamlet  {

  val in = AvroInlet[TransactionModel]("in")

  val out = AvroOutlet[TransactionModel]("out").withPartitioner(RoundRobinPartitioner)

  override def shape(): StreamletShape = StreamletShape.withInlets(in).withOutlets(out)

  override def createLogic = new RunnableGraphStreamletLogic() {

    def runnableGraph(): RunnableGraph[_] = sourceWithCommittableContext(in).to(committableSink(out))
  }
}
