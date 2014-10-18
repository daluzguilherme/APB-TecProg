/**
 * ServicoExceptions
 * ServicoExceptions include constructors to create a exceptions for the erros related to service.
 */
package exception;

@SuppressWarnings("serial")
public class ServiceException extends Exception{
	
	/* Constructor creates a ServicoExcpetion without any argument. */
	public ServiceException() {
		super();
	}

	/* Constructor creates a ServicoExcpetion with a message. */
	public ServiceException(String message) {
		super(message);
	}
}
