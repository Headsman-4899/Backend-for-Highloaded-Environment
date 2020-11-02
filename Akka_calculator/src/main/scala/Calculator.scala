import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._
import akka.actor.typed.ActorRef
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors


object Greeter {
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: String, from: ActorRef[Greet])

  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    context.log.info(message.whom)
    message.replyTo ! Greeted(message.whom, context.self)
    Behaviors.same
  }
}

object GreeterBot {

  def apply(): Behavior[Greeter.Greeted] = {
    Behaviors.receive { (context, message) =>
      var numbers: ListBuffer[String] = new ListBuffer[String]
      var operations: ListBuffer[Char] = new ListBuffer[Char]
      var number: String = ""
      var secondNum: Boolean = false
      for (x <- 0 until message.whom.length) {
        if (message.whom(x).equals('+') || message.whom(x).equals('-') || message.whom(x).equals('*') || message.whom(x).equals('/')) {
          numbers += number
          number = ""
          operations += message.whom(x)
        }
        else {
          number = number + message.whom(x)

        }
      }
      numbers += number

      var oper = new Operation {}

      //println(numbers)
      //println(operations)
      for (x <- 0 until operations.length) {
        if (operations(x).equals('+')) {
          oper = new Plus(numbers(0).toDouble, numbers(x + 1).toDouble)
          numbers.update(0, oper.calculate().toString)
        }
        if (operations(x).equals('-')) {
          oper = new Minus(numbers(0).toDouble, numbers(x + 1).toDouble)
          numbers.update(0, oper.calculate().toString)
        }
        if (operations(x).equals('*')) {
          oper = new Multiply(numbers(0).toDouble, numbers(x + 1).toDouble)
          numbers.update(0, oper.calculate().toString)
        }
        if (operations(x).equals('/')) {
          oper = new Divide(numbers(0).toDouble, numbers(x + 1).toDouble)
          numbers.update(0, oper.calculate().toString)
        }
      }
      context.log.info(numbers(0))
      Behaviors.stopped
    }


    //
  }
}

object GreeterMain {

  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>
      val greeter = context.spawn(Greeter(), "greeter")

      Behaviors.receiveMessage { message =>
        val replyTo = context.spawn(GreeterBot(), message.name)
        greeter ! Greeter.Greet(message.name, replyTo)
        Behaviors.same
      }
    }
}

object Main extends App {
  var exp :String = scala.io.StdIn.readLine()
  val greeterMain: ActorSystem[GreeterMain.SayHello] = ActorSystem(GreeterMain(), "AkkaQuickStart")
  greeterMain ! GreeterMain.SayHello(exp)
}
