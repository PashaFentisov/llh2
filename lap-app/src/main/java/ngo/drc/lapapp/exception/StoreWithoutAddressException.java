package ngo.drc.lapapp.exception;

import org.springframework.http.HttpStatus;

public class StoreWithoutAddressException extends GenericDisplayableException{
    public StoreWithoutAddressException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }


}
