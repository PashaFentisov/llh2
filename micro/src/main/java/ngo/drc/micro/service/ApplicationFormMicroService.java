package ngo.drc.micro.service;

import ngo.drc.core.dto.GenericFormResponse;
import ngo.drc.core.exceptionHandling.PageResponse;
import ngo.drc.micro.dto.ApplicationFormMicroResponseDto;
import ngo.drc.micro.dto.ApplicationFormMicroSavingDto;
import ngo.drc.micro.dto.ApplicationFormMicroUpdateDto;
import ngo.drc.micro.dto.LawyerStatusRequest;
import ngo.drc.micro.form.ApplicationFormMicroInfo;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ApplicationFormMicroService {

    ApplicationFormMicroResponseDto saveApplicationFormMicro(ApplicationFormMicroSavingDto applicationFormMicroSavingDto);

    void deleteApplicationFormMicro(UUID id);

    void setAsNotDeletedApplicationFormMicro(UUID id);

    ApplicationFormMicroResponseDto updateApplicationFormMicro(ApplicationFormMicroUpdateDto applicationFormMicroUpdateDto, UUID id, String email);

    GenericFormResponse<ApplicationFormMicroInfo, ApplicationFormMicroResponseDto> getApplicationFormMicro(UUID id, String email);

    GenericFormResponse<ApplicationFormMicroInfo, PageResponse<ApplicationFormMicroResponseDto>> getAllApplicationFormsMicro(Pageable pageable);

    ApplicationFormMicroResponseDto revertApplicationFormMicroToLastVersion(UUID id);

    ApplicationFormMicroResponseDto setLawyerStatusForApplicationFormMicro(UUID id, LawyerStatusRequest lawyerStatusRequest);
}
