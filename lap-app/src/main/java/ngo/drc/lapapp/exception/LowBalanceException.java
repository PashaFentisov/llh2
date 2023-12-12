package ngo.drc.lapapp.exception;

import org.springframework.http.HttpStatus;

public class LowBalanceException extends GenericDisplayableException {
    public LowBalanceException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
