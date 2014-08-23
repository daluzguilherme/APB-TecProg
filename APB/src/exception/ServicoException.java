package exception;

@SuppressWarnings("serial")
public class ServicoException extends Exception{
	
	public ServicoException() {
		super();
	}

	public ServicoException(String message) {
		super(message);
	}
}
