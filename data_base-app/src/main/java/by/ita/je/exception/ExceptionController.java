package by.ita.je.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundData.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ValidationError> handleCustomException(NotFoundData exception) {
        return new ResponseEntity<>(getValidationError(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotCorrectData.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ValidationError> handleCustomException(NotCorrectData exception) {
        return new ResponseEntity<>(getValidationError(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotCorrectSeat.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ValidationError> handleCustomException(NotCorrectSeat exception) {
        return new ResponseEntity<>(getValidationError(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ValidationError> handleCustomException(MethodArgumentNotValidException ex) {
        ValidationError error = getValidationError(ex);
        StringBuilder message= new StringBuilder("");
        for (FieldError errors : ex.getBindingResult().getFieldErrors()) {
            message.append(errors.getField() + ": " + errors.getDefaultMessage() + "; ");
        }
        for (ObjectError errors : ex.getBindingResult().getGlobalErrors()) {
            message.append(errors.getObjectName() + ": " + errors.getDefaultMessage() + "; ");
        }
        error.setMessage(message.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseEntity<ValidationError> handleUnprocessedException(Exception exception) {
        return new ResponseEntity<>(getValidationError(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ValidationError getValidationError(Exception ex){
        return ValidationError.builder()
                .exception(ex.getClass().getSimpleName())
                .message(ex.getMessage())
                .build();
    }
}
