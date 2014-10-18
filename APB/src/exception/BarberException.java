/**
 * BarbeiroExceptions
 * BarbeiroExceptions include constructors to create a exceptions for the erros related to barber.
 */
package exception;

@SuppressWarnings("serial")
public class BarberException extends Exception {

	/* Constructor creates a BarbeiroExcpetion without any argument. */
	public BarberException() {
		super();
	}

	/* Constructor creates a BarbeiroExcpetion with a message. */
	public BarberException(String message) {
		super(message);
	}

}
