/**
 * ReciboExceptions
 * ReciboExceptions include constructors to create a exceptions for the erros related to receipt.
 */
package exception;

@SuppressWarnings("serial")
public class ReceiptException extends Exception {

	/* Constructor creates a ReciboExcpetion without any argument. */
	public ReceiptException() {
		super();
	}

	/* Constructor creates a ReciboExcpetion with a message. */
	public ReceiptException(String message) {
		super(message);
	}

	
}
