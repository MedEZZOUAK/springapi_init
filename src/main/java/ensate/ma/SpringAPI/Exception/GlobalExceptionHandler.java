package ensate.ma.SpringAPI.Exception;

import ensate.ma.SpringAPI.DAO.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CandidatureNotFoundException.class)
    public ResponseEntity<String> handleCandidatureNotFoundException(CandidatureNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BourseNotFoundException.class)
    public ResponseEntity<String> handleBourseNotFoundException(BourseNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

  @ExceptionHandler(InvalidJwtException.class)
  public ResponseEntity<ErrorResponse> handleInvalidJwtException(InvalidJwtException ex) {
    logger.error("JWT expired:");
    ErrorResponse errorResponse = new ErrorResponse(
            "JWT expired",
            "401"
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }
}
