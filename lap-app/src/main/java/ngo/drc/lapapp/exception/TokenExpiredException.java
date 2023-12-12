package ngo.drc.lapapp.exception;

import org.springframework.http.HttpStatus;

public class TokenExpiredException extends GenericDisplayableException {
    public TokenExpiredException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
