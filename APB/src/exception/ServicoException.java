/**
 * ServicoExceptions
 * ServicoExceptions include constructors to create a exceptions for the erros related to service.
 */
package exception;

@SuppressWarnings("serial")
public class ServicoException extends Exception{
	
	/* Constructor creates a ServicoExcpetion without any argument. */
	public ServicoException() {
		super();
	}

	/* Constructor creates a ServicoExcpetion with a message. */
	public ServicoException(String message) {
		super(message);
	}
}
