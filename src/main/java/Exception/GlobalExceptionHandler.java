package Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
		ErrorResponse ErrorResponse = 
				new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(ErrorResponse, HttpStatus.NOT_FOUND);
	}

	// handling global exception
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
		ErrorResponse ErrorResponse = 
				new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(ErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
