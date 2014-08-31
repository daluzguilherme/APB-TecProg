/**
 * RelatorioExceptions
 * RelatorioExceptions include constructors to create a exceptions for the erros related to report.
 */
package exception;

@SuppressWarnings("serial")
public class RelatorioException extends Exception {

	/* Constructor creates a RelatorioExcpetion without any argument. */
	public RelatorioException() {
		super();
	}

	/* Constructor creates a RelatorioExcpetion with a message. */
	public RelatorioException(String message) {
		super(message);
	}

}