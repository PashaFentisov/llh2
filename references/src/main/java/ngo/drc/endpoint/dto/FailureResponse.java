package ngo.drc.endpoint.dto;


import lombok.Getter;
import lombok.Setter;
import ngo.drc.endpoint.BaseResponse;
import ngo.drc.endpoint.Status;


@Getter
@Setter
public class FailureResponse extends BaseResponse {
    public FailureResponse(String message) {
        super(Status.FAIL, message);
    }

}