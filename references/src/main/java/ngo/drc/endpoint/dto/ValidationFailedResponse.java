package ngo.drc.endpoint.dto;


import lombok.Getter;
import ngo.drc.endpoint.BaseResponse;
import ngo.drc.endpoint.Status;

import java.util.List;
@Getter
public class ValidationFailedResponse extends BaseResponse {
    private final List<String> errorMessages;
    public ValidationFailedResponse(String message, List<String> errorMessages) {
        super(Status.FAIL, message);
        this.errorMessages = errorMessages;
    }
}
