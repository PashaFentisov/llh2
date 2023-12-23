package ngo.drc.micro.service;

import ngo.drc.core.dto.GenericFormResponse;
import ngo.drc.core.dto.GenericPageFormResponse;
import ngo.drc.endpoint.PageResponse;
import ngo.drc.micro.dto.MainFormResponseDto;
import ngo.drc.micro.dto.MainFormSavingDto;
import ngo.drc.micro.dto.MainFormUpdateDto;
import ngo.drc.micro.form.MainFormInfo;
import org.springframework.data.domain.Pageable;

public interface MainFormService {

    MainFormResponseDto saveMicroMainForm(MainFormSavingDto mainFormSavingDto);


    void deleteMicroMainForm(Long id);

    MainFormResponseDto updateMicroMainForm(MainFormUpdateDto mainFormUpdateDto, Long id);

    GenericFormResponse<MainFormInfo, MainFormResponseDto> getMainForm(Long id);

    GenericPageFormResponse<MainFormInfo, PageResponse<MainFormResponseDto>> getAllMainForms(Pageable pageable);
}
