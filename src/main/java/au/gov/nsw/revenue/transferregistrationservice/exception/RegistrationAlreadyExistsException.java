package au.gov.nsw.revenue.transferregistrationservice.exception;

public class RegistrationAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegistrationAlreadyExistsException() {
	}

	public RegistrationAlreadyExistsException(String message) {
		super(message);
	}

	public RegistrationAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public RegistrationAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegistrationAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
