/**
 * AgendaExceptions
 * AgendaExceptions include constructors to create a exceptions for the erros related to address book.
 */
package exception;

@SuppressWarnings("serial")
public class AddressBookException extends Exception {

	/* Constructor creates a AgendaExcpetion without any argument. */
	public AddressBookException() {
		super();
	}

	/* Constructor creates a AgendaExcpetion with a message. */
	public AddressBookException(String message) {
		super(message);
	}

}
