import akka.actor.typed.ActorSystem
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import  io.circe.generic.auto._
import akka.http.scaladsl.server.{Directives, Route}

import scala.concurrent.ExecutionContext

trait Router {
  def route: Route
}

class MyRouter(val addressBookRepository: AddressBookRepository)(implicit system: ActorSystem[_],  ex:ExecutionContext)
  extends  Router
    with Directives
    with HealthCheckRoute
    with ValidatorDirectives
    with AddressBookDirectives {

  def address = {
    pathPrefix("addresses") {
      concat(
        pathEndOrSingleSlash {
          concat(
            get {
              complete(addressBookRepository.all())
            },
            post {
              entity(as[CreateAddressBook]) { createAddressBook =>
                validateWith(CreateAddressBookValidator)(createAddressBook) {
                  handleWithGeneric(addressBookRepository.create(createAddressBook)) { addressBook =>
                    complete(addressBook)
                  }
                }
              }
            }

          )
        } ~
        path(Segment) { id =>
          concat(
          put {
            entity(as[UpdateAddressBook]) { updateAddressBook =>
              validateWith(UpdateAddressBookValidator)(updateAddressBook) {
                handle(addressBookRepository.update(id, updateAddressBook)) {
                  case AddressBookRepository.AddressBookNotFound(_) =>
                    ApiError.addressBookNotFound(id)
                  case _ =>
                    ApiError.generic
                } { addressBook =>
                  complete(addressBook)
                }
              }
            }
          },
          get {
            complete(addressBookRepository.getAddressBook(id))
          },
          delete {
            complete(addressBookRepository.deleteAddressBook(id))
          }
          )
        },
        path("done") {
          get {
            complete(addressBookRepository.done())
          }
        },
        path("pending") {
          get {
            complete(addressBookRepository.pending())
          }
        },


      )
    }
  }

  override def route = {
    concat(
      healthCheck,
      address
    )
  }
}
