package ngo.drc.core.exception;

import org.springframework.http.HttpStatus;

public class UserDeletedException extends GenericDisplayableException {
    public UserDeletedException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
