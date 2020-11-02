import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import org.slf4j.{Logger, LoggerFactory}

import scala.util.Try


object Main {

  def main(args: Array[String]): Unit = {

  implicit val log: Logger = LoggerFactory.getLogger(getClass)

  val rootBehavior = Behaviors.setup[Nothing] { context =>

    val addresses: Seq[AddressBook] = Seq(
      AddressBook("1", "Daulet", "Almaty", true),
      AddressBook("2", "Turar", "Shymkent", false),
      AddressBook("3", "Bekyrys", "Aktau", true),
      AddressBook("4", "Vitaliy", "Almaty", false)
    )

    val AddressBookRepository = new InMemoryAddressBookRepository(addresses)(context.executionContext)
    val router = new MyRouter(AddressBookRepository)(context.system, context.executionContext)

    val host = "0.0.0.0"
    val port = Try(System.getenv("PORT")).map(_.toInt).getOrElse(9000)

    Server.startHttpServer(router.route, host, port)(context.system, context.executionContext)
    Behaviors.empty
  }
  val system = ActorSystem[Nothing](rootBehavior, "HelloAkkaHttpServer")
}

}
