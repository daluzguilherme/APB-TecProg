/**
 * BarbeiroExceptions
 * BarbeiroExceptions include constructors to create a exceptions for the erros related to barber.
 */
package exception;

@SuppressWarnings("serial")
public class BarbeiroException extends Exception {

	/* Constructor creates a BarbeiroExcpetion without any argument. */
	public BarbeiroException() {
		super();
	}

	/* Constructor creates a BarbeiroExcpetion with a message. */
	public BarbeiroException(String message) {
		super(message);
	}

}
