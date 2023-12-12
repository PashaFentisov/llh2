package ngo.drc.lapapp.endpoint.dto;


import lombok.Getter;
import lombok.Setter;
import ngo.drc.lapapp.endpoint.BaseResponse;
import ngo.drc.lapapp.endpoint.Status;

@Getter
@Setter
public class FailureResponse extends BaseResponse {
    public FailureResponse(String message) {
        super(Status.FAIL, message);
    }

}