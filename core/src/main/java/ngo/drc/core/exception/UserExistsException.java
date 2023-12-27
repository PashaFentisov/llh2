package ngo.drc.core.exception;

import org.springframework.http.HttpStatus;

public class UserExistsException extends GenericDisplayableException {
    public UserExistsException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
