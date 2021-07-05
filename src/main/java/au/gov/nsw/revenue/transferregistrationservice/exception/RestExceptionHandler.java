package au.gov.nsw.revenue.transferregistrationservice.exception;

import java.util.Arrays;

import au.gov.nsw.revenue.transferregistrationservice.openapi.model.TransferRegistrationApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
        TransferRegistrationApiError xferRegistrationError = new TransferRegistrationApiError();
        xferRegistrationError.setStatus(HttpStatus.BAD_REQUEST.name());
        xferRegistrationError.setMessage(error);
        xferRegistrationError.setDebugMessage(ex.getLocalizedMessage());
		return new ResponseEntity<>(xferRegistrationError,HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
        TransferRegistrationApiError xferRegistrationError = new TransferRegistrationApiError();
        xferRegistrationError.setStatus(HttpStatus.BAD_REQUEST.name());
        xferRegistrationError.setMessage(error);
        xferRegistrationError.setDebugMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(xferRegistrationError,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RegistrationAlreadyExistsException.class)
	protected ResponseEntity<Object> handleEntityAlreadyExists(RegistrationAlreadyExistsException ex) {
		TransferRegistrationApiError xferRegistrationError = new TransferRegistrationApiError();
		xferRegistrationError.setStatus(HttpStatus.CONFLICT.name());
        xferRegistrationError.setMessage(ex.getMessage());
        xferRegistrationError.setDebugMessage(ex.getStackTrace() != null ? Arrays.toString(ex.getStackTrace()) : ex.getMessage());
        return new ResponseEntity<>(xferRegistrationError,HttpStatus.CONFLICT);
	}

	@ExceptionHandler(PersonAlreadyExistsException.class)
	protected ResponseEntity<Object> handlePersonAlreadyExists(PersonAlreadyExistsException ex) {
		TransferRegistrationApiError xferRegistrationError = new TransferRegistrationApiError();
		xferRegistrationError.setStatus(HttpStatus.CONFLICT.name());
		xferRegistrationError.setMessage(ex.getMessage());
		xferRegistrationError.setDebugMessage(ex.getStackTrace() != null ? Arrays.toString(ex.getStackTrace()) : ex.getMessage());
		return new ResponseEntity<>(xferRegistrationError,HttpStatus.CONFLICT);
	}

	@ExceptionHandler(PersonNotFoundException.class)
	protected ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException ex) {
		TransferRegistrationApiError xferRegistrationError = new TransferRegistrationApiError();
		xferRegistrationError.setStatus(HttpStatus.NOT_FOUND.name());
		xferRegistrationError.setMessage(ex.getMessage());
		xferRegistrationError.setDebugMessage(ex.getStackTrace() != null ? Arrays.toString(ex.getStackTrace()) : ex.getMessage());
		return new ResponseEntity<>(xferRegistrationError,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(VehicleAlreadyExistsException.class)
	protected ResponseEntity<Object> handlePersonAlreadyExists(VehicleAlreadyExistsException ex) {
		TransferRegistrationApiError xferRegistrationError = new TransferRegistrationApiError();
		xferRegistrationError.setStatus(HttpStatus.CONFLICT.name());
		xferRegistrationError.setMessage(ex.getMessage());
		xferRegistrationError.setDebugMessage(ex.getStackTrace() != null ? Arrays.toString(ex.getStackTrace()) : ex.getMessage());
		return new ResponseEntity<>(xferRegistrationError,HttpStatus.CONFLICT);
	}

	@ExceptionHandler(VehicleNotFoundException.class)
	protected ResponseEntity<Object> handleVehicleNotFoundException(VehicleNotFoundException ex) {
		TransferRegistrationApiError xferRegistrationError = new TransferRegistrationApiError();
		xferRegistrationError.setStatus(HttpStatus.NOT_FOUND.name());
		xferRegistrationError.setMessage(ex.getMessage());
		xferRegistrationError.setDebugMessage(ex.getStackTrace() != null ? Arrays.toString(ex.getStackTrace()) : ex.getMessage());
		return new ResponseEntity<>(xferRegistrationError,HttpStatus.NOT_FOUND);
	}
}