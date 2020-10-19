import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.actor.typed.scaladsl.{AbstractBehavior,ActorContext,Behaviors}
import akka.actor.typed.receptionist.{Receptionist,ServiceKey}

object Mouth {

  sealed trait MessageToMouth
  case class SpeakText(msg: String) extends MessageToMouth
  private case class ListingResponse(listing: Receptionist.Listing)
    extends MessageToMouth

  // (1) a ServiceKey is a unique identifier for this actor
  val MouthKey: ServiceKey[MessageToMouth] = ServiceKey("Mouth")

  // this line of code is long, so i wrapped it onto two lines
  def apply(): Behavior[MessageToMouth] = Behaviors.setup {
    context: ActorContext[MessageToMouth] =>

      // (2) every actor that wants to be discoverable must register itself
      // with the Receptionist by sending the Receptionist as Receptionist
      // message, including your ServiceKey
      context.system.receptionist !
        Receptionist.Register(Mouth.MouthKey, context.self)

      Behaviors.receiveMessage { message =>
        message match {
          case SpeakText(msg) =>
            println(s"Mouth: got a msg: $msg")
            Behaviors.same
        }
      }
  }

}