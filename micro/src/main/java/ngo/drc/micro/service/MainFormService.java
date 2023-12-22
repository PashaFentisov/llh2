package ngo.drc.micro.service;

import ngo.drc.micro.dto.MainFormResponseDto;
import ngo.drc.micro.dto.MainFormSavingDto;

public interface MainFormService {

    MainFormResponseDto saveMicroMainForm(MainFormSavingDto mainFormSavingDto);


}
