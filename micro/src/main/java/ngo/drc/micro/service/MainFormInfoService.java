package ngo.drc.micro.service;

import ngo.drc.micro.form.MainFormInfo;

import java.util.Map;

public interface MainFormInfoService {
    MainFormInfo getMainFormInfo();

    Map<String, String> getStatuses();
}
