package in.habel.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthenticated")
public class UnauthenticatedException extends AuthenticationException {


    public UnauthenticatedException(String message) {
        super(message);
    }
}