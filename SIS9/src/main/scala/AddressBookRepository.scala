import java.util.UUID

import AddressBookRepository.AddressBookNotFound

import scala.concurrent.{ExecutionContext, Future}

trait AddressBookRepository {
  def all(): Future[Seq[AddressBook]]
  def done(): Future[Seq[AddressBook]]
  def pending(): Future[Seq[AddressBook]]
  def create(createTodo:CreateAddressBook): Future[AddressBook]
  def update(id: String, updateAddressBook: UpdateAddressBook): Future[AddressBook]
  def getAddressBook(id: String): Future[Option[AddressBook]]
  def deleteAddressBook(id: String): Future[Unit]
}


object AddressBookRepository {
  final case class AddressBookNotFound(id: String) extends Exception(s"Address Book with id $id not found.")
}

class InMemoryAddressBookRepository(initialAddresses: Seq[AddressBook] = Seq.empty)(implicit ec: ExecutionContext) extends AddressBookRepository {

  private var Addresses: Vector[AddressBook] = initialAddresses.toVector

  override def all(): Future[Seq[AddressBook]] = Future.successful(Addresses)

  override def done(): Future[Seq[AddressBook]] = Future.successful(Addresses.filter(_.done))

  override def pending(): Future[Seq[AddressBook]] = Future.successful(Addresses.filterNot(_.done))

  override def create(createAddressBook: CreateAddressBook): Future[AddressBook] = Future.successful {
    val address = AddressBook(
      id = UUID.randomUUID().toString,
      name = createAddressBook.name,
      address = createAddressBook.address,
      done = false
    )
    Addresses = Addresses :+ address
    address
  }

  override def update(id: String, updateAddressBook: UpdateAddressBook): Future[AddressBook] = {
    Addresses.find(_.id == id) match {
      case Some(foundAddressBook) =>
        val newAddressBook = updateHelper(foundAddressBook, updateAddressBook)
        Addresses = Addresses.map(t => if (t.id == id) newAddressBook else t)
        Future.successful(newAddressBook)
      case None =>
        Future.failed(AddressBookNotFound(id))
    }
  }

  private def updateHelper(addressBook: AddressBook, updateAddressBook: UpdateAddressBook): AddressBook = {
    //val t3 = updateAddressBook.id.map(id => addressBook.copy(id = id.toString))
    val t1 = updateAddressBook.name.map(name => addressBook.copy(name = name)).getOrElse(addressBook)
    val t2 = updateAddressBook.address.map(address => t1.copy(address = address)).getOrElse(t1)
    updateAddressBook.done.map(done => t2.copy(done = done)).getOrElse(t2)
  }

  override def getAddressBook(id: String): Future[Option[AddressBook]] = Future.successful {
    Addresses.find(_.id == id)
  }

  override def deleteAddressBook(id: String): Future[Unit] = Future.successful {
    Addresses = Addresses.filterNot(_.id == id)
  }

}