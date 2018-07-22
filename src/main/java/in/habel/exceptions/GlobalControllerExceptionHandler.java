package in.habel.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("unused")
@Slf4j
@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ResponseEntity<ErrorModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage;
        try {
            errorMessage = String.format("Validation error : field %s %s", ex.getBindingResult().getFieldErrors().get(0).getField(), ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        } catch (Exception e) {
            errorMessage = ex.getLocalizedMessage();
        }
        return new ResponseEntity<>(new ErrorModel(404, errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, BadRequestException.class})
    @ResponseBody
    ResponseEntity<ErrorModel> handleBadRequestException(Exception ex) {
        return new ResponseEntity<>(new ErrorModel(400, ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    ResponseEntity<ErrorModel> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorModel(404, ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    ResponseEntity<ErrorModel> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(new ErrorModel(404, ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    ResponseEntity<ErrorModel> handleBadCredentialsException(UnauthenticatedException ex) {
        return new ResponseEntity<>(new ErrorModel(401, ex.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
    }

}