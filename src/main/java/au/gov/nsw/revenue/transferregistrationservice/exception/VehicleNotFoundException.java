package au.gov.nsw.revenue.transferregistrationservice.exception;

public class VehicleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public VehicleNotFoundException(String message) {
        super(message);
    }
}
