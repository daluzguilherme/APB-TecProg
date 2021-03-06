/**
 * RelatorioExceptions
 * RelatorioExceptions include constructors to create a exceptions for the erros related to report.
 */
package exception;

@SuppressWarnings("serial")
public class ReportException extends Exception {

	/* Constructor creates a RelatorioExcpetion without any argument. */
	public ReportException() {
		super();
	}

	/* Constructor creates a RelatorioExcpetion with a message. */
	public ReportException(String message) {
		super(message);
	}

}