package au.gov.nsw.revenue.transferregistrationservice.exception;

public class PersonAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
