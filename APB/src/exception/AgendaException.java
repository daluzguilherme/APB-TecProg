/**
 * AgendaExceptions
 * AgendaExceptions include constructors to create a exceptions for the erros related to address book.
 */
package exception;

@SuppressWarnings("serial")
public class AgendaException extends Exception {

	/* Constructor creates a AgendaExcpetion without any argument. */
	public AgendaException() {
		super();
	}

	/* Constructor creates a AgendaExcpetion with a message. */
	public AgendaException(String message) {
		super(message);
	}

}
