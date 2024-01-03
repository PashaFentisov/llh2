package ngo.drc.core.exception;

import org.springframework.http.HttpStatus;

public class StatusException extends GenericDisplayableException {
    public StatusException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
