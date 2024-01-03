package ngo.drc.micro.service;

import ngo.drc.core.security.entity.Role;
import ngo.drc.micro.enumeration.MicroStatus;
import ngo.drc.micro.form.MainFormInfo;

import java.util.Map;

public interface MainFormInfoService {
    MainFormInfo getMainFormInfo();

    Map<String, String> getAllStatuses();

    Map<String, String> getNextStatusesByCurrentStatus(MicroStatus currentStatus, Role role);
}
