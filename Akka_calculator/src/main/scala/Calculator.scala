import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior }
import scala.io.StdIn.readDouble

object Calculator {

  val x = readDouble()

  sealed trait Command
  case class Add(value: Double, replyTo: ActorRef[Added]) extends Command
  case class Subtract(value: Double, replyTo: ActorRef[Subtracted]) extends Command
  case class Divide(value: Double, replyTo: ActorRef[Divided]) extends Command
  case class Multiply(value: Double, replyTo: ActorRef[Multiplied]) extends Command

  case object PrintResult extends Command
  case object GetResult extends Command


  sealed trait Event
  case class Added(value: Double, replyTo: ActorRef[Add]) extends Event
  case class Subtracted(value: Double, replyTo: ActorRef[Subtract]) extends Event
  case class Divided(value: Double, replyTo: ActorRef[Divide]) extends Event
  case class Multiplied(value: Double, replyTo: ActorRef[Multiply]) extends Event

  def apply():Behavior[Add] = Behaviors.receive {
    (context, message) =>
      context.log.info("added: ", message.value)
      message.replyTo ! Added(message.value, context.self)
      Behaviors.same
  }


}
