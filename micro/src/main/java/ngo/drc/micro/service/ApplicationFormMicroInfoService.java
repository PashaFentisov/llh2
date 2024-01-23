package ngo.drc.micro.service;

import ngo.drc.core.security.entity.Role;
import ngo.drc.micro.enumeration.MicroStatus;
import ngo.drc.micro.form.ApplicationFormMicroInfo;

import java.util.Map;

public interface ApplicationFormMicroInfoService {
    ApplicationFormMicroInfo getApplicationFormMicroInfo();

    Map<String, String> getAllStatuses();

    Map<String, String> getNextStatusesByCurrentStatus(MicroStatus currentStatus, Role role);
}
