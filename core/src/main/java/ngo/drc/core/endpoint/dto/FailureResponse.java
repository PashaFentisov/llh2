package ngo.drc.core.endpoint.dto;


import lombok.Getter;
import lombok.Setter;
import ngo.drc.core.endpoint.BaseResponse;
import ngo.drc.core.endpoint.Status;


@Getter
@Setter
public class FailureResponse extends BaseResponse {
    public FailureResponse(String message) {
        super(Status.FAIL, message);
    }

}