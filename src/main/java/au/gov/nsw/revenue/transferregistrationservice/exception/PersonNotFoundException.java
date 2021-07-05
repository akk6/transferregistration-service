package au.gov.nsw.revenue.transferregistrationservice.exception;

public class PersonNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PersonNotFoundException() {
    }
    public PersonNotFoundException(String message) {
        super(message);
    }

    public PersonNotFoundException(Throwable cause) {
        super(cause);
    }

    public PersonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
