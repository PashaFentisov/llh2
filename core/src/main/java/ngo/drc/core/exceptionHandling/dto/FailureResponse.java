package ngo.drc.core.exceptionHandling.dto;


import lombok.Getter;
import lombok.Setter;
import ngo.drc.core.exceptionHandling.BaseResponse;
import ngo.drc.core.exceptionHandling.Status;


@Getter
@Setter
public class FailureResponse extends BaseResponse {
    public FailureResponse(String message) {
        super(Status.FAIL, message);
    }

}