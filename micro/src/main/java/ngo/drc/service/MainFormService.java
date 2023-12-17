package ngo.drc.service;

import ngo.drc.bundle.form.MicroMainForm;
import ngo.drc.dto.MainFormSavingDto;

public interface MainFormService {
    MicroMainForm getMainFormInfo();

    void saveMicroMainFormInfo(MainFormSavingDto mainFormSavingDto);
}
