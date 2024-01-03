package ngo.drc.core.exception;

import org.springframework.http.HttpStatus;

public class PermissionException extends GenericDisplayableException {
    public PermissionException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
