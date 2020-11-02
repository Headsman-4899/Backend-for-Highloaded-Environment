trait Validator[T] {
  def validate(t: T): Option[ApiError]
}

object CreateAddressBookValidator extends Validator[CreateAddressBook] {
  def validate(createAddressBook: CreateAddressBook): Option[ApiError] =
    if (createAddressBook.name.isEmpty)
      Some(ApiError.emptyTitleField)
    else
      None
}

object UpdateAddressBookValidator extends Validator[UpdateAddressBook] {

  def validate(updateAddressBook: UpdateAddressBook): Option[ApiError] =
    if (updateAddressBook.address.exists(_.isEmpty))
      Some(ApiError.emptyTitleField)
    else
      None
}