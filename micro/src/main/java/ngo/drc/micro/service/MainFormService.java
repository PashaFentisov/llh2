package ngo.drc.micro.service;

import ngo.drc.core.dto.GenericFormResponse;
import ngo.drc.core.endpoint.PageResponse;
import ngo.drc.micro.dto.MainFormResponseDto;
import ngo.drc.micro.dto.MainFormSavingDto;
import ngo.drc.micro.dto.MainFormUpdateDto;
import ngo.drc.micro.form.MainFormInfo;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MainFormService {

    MainFormResponseDto saveMicroMainForm(MainFormSavingDto mainFormSavingDto);

    void deleteMicroMainForm(UUID id);

    void setAsNotDeletedMainForm(UUID id);

    MainFormResponseDto updateMicroMainForm(MainFormUpdateDto mainFormUpdateDto, UUID id);

    GenericFormResponse<MainFormInfo, MainFormResponseDto> getMainForm(UUID id, String email);

    GenericFormResponse<MainFormInfo, PageResponse<MainFormResponseDto>> getAllMainForms(Pageable pageable);

    MainFormResponseDto revertMainFormToLastVersion(UUID id);
}
