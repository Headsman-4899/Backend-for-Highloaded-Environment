import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.actor.typed.scaladsl.{AbstractBehavior,ActorContext,Behaviors}
import akka.actor.typed.receptionist.{Receptionist,ServiceKey}
import akka.actor.typed.receptionist.Receptionist.{Find, Listing}
import akka.util.Timeout
import scala.concurrent.duration._
import scala.util.{Failure, Random, Success}

object Brain {

  // our messages
  sealed trait MessageToBrain
  final case object FindTheMouth extends MessageToBrain
  final case class BrainFailure(s: String) extends MessageToBrain
  final case class FoundTheMouth(listing: Listing) extends MessageToBrain

  // the usual factory method
  def apply(): Behavior[MessageToBrain] =
    Behaviors.setup { context: ActorContext[MessageToBrain] =>

      // (1) we need to find the mouth, so this needs to be a var
      // field, and it uses Option and None
      var mouth: Option[ActorRef[Mouth.MessageToMouth]] = None

      Behaviors.receiveMessage { message =>
        message match {
          case FindTheMouth =>
            // (2) when we receive this message, we use `ask` and a
            // timeout value to try to find the Mouth actor using
            // its MouthKey. in response, the Receptionist will
            // either give us a Success or Failure value.
            // when we get a Success value, we send ourself the
            // `FoundTheMouth(listing)` message. this isn’t
            // completely necessary, but i did it to simplify the
            // code in this area.
            println("Brain: Got a FindTheMouth message")
            implicit val timeout: Timeout = 1.second
            context.ask(
              context.system.receptionist,
              Find(Mouth.MouthKey)
            ) {
              case Success(listing: Listing) =>
                Brain.FoundTheMouth(listing)
              case Failure(_) =>
                Brain.BrainFailure("Error: Could not find Mouth")
            }
            Behaviors.same

          case FoundTheMouth(listing) =>
            // (3) this is the message we sent to ourself in Step 2.
            // as with the example in the Solution, the Receptionist
            // sent us a Set of Mouth actor references.
            println("Brain: Got a FoundTheMouth message")
            val instances: Set[ActorRef[Mouth.MessageToMouth]] =
              listing.serviceInstances(Mouth.MouthKey)
            // (4) for this example i know there should only be one
            // Mouth ActorRef in the Set, so i assign it to the
            // `mouth` variable i created earlier. if this succeeds,
            // it sends the SpeakText message to the Mouth.
            mouth = instances.headOption
            mouth.foreach { m =>
              m ! Mouth.SpeakText("Brain says hello to Mouth.")
            }
            Behaviors.same
          case BrainFailure(msg) =>
            // (5) if the process in Step 2 fails, we send ourself
            // the BrainFailure message, which is printed here.
            println(msg)
            Behaviors.same
        }
      }
    }
}