package clear.solutions.testtask.common;

import clear.solutions.testtask.exception.IncorrectAgeException;
import clear.solutions.testtask.exception.IncorrectDateRangeException;
import clear.solutions.testtask.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorResponse> validationErrors = new ArrayList<>();
        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError error : bindingResult.getFieldErrors()) {
            validationErrors.add(new ErrorResponse("400",
                    String.format("%s %s", error.getField(), error.getDefaultMessage())));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("404", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IncorrectAgeException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectAgeException(IncorrectAgeException ex) {
        ErrorResponse errorResponse = new ErrorResponse("400", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IncorrectDateRangeException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectDateRangeException(IncorrectDateRangeException ex) {
        ErrorResponse errorResponse = new ErrorResponse("400", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}