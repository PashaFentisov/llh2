package ngo.drc.core.exceptionHandling.dto;


import lombok.Getter;
import ngo.drc.core.exceptionHandling.BaseResponse;
import ngo.drc.core.exceptionHandling.Status;

import java.util.List;
@Getter
public class ValidationFailedResponse extends BaseResponse {
    private final List<String> errorMessages;
    public ValidationFailedResponse(String message, List<String> errorMessages) {
        super(Status.FAIL, message);
        this.errorMessages = errorMessages;
    }
}
