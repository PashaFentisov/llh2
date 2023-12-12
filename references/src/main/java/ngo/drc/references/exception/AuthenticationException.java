package ngo.drc.references.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends GenericDisplayableException{
    public AuthenticationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
