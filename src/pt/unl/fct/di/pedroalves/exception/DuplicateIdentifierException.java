package pt.unl.fct.di.pedroalves.exception;

/**
 * 
 * @author pedroalves
 *
 */
public class DuplicateIdentifierException extends RuntimeException {

	public DuplicateIdentifierException() {
		super();
	}

	public DuplicateIdentifierException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateIdentifierException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateIdentifierException(String message) {
		super(message);
	}

	public DuplicateIdentifierException(Throwable cause) {
		super(cause);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
