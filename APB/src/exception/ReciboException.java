/**
 * ReciboExceptions
 * ReciboExceptions include constructors to create a exceptions for the erros related to receipt.
 */
package exception;

@SuppressWarnings("serial")
public class ReciboException extends Exception {

	/* Constructor creates a ReciboExcpetion without any argument. */
	public ReciboException() {
		super();
	}

	/* Constructor creates a ReciboExcpetion with a message. */
	public ReciboException(String message) {
		super(message);
	}

	
}
