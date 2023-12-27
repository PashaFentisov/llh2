package ngo.drc.core.exception;

import org.springframework.http.HttpStatus;

public class UserIsNotVerifiedException extends GenericDisplayableException {
    public UserIsNotVerifiedException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
