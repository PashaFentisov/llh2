package ngo.drc.micro.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ngo.drc.core.dto.GenericFormResponse;
import ngo.drc.core.exception.PermissionException;
import ngo.drc.core.exception.StatusException;
import ngo.drc.core.exceptionHandling.PageResponse;
import ngo.drc.core.exceptionHandling.mapper.PageMapper;
import ngo.drc.core.security.entity.User;
import ngo.drc.core.security.repository.UserRepository;
import ngo.drc.micro.dto.ApplicationFormMicroResponseDto;
import ngo.drc.micro.dto.ApplicationFormMicroSavingDto;
import ngo.drc.micro.dto.ApplicationFormMicroUpdateDto;
import ngo.drc.micro.dto.LawyerStatusRequest;
import ngo.drc.micro.entity.ApplicationFormMicro;
import ngo.drc.micro.entity.ApplicationFormMicroLastVersion;
import ngo.drc.micro.enumeration.MicroDonor;
import ngo.drc.micro.enumeration.MicroStatus;
import ngo.drc.micro.form.ApplicationFormMicroInfo;
import ngo.drc.micro.mapper.ApplicationFormMicroResponseMapper;
import ngo.drc.micro.mapper.ApplicationFormMicroSavingMapper;
import ngo.drc.micro.repository.ApplicationFormMicroLastVersionRepository;
import ngo.drc.micro.repository.ApplicationFormMicroRepository;
import ngo.drc.micro.service.ApplicationFormMicroInfoService;
import ngo.drc.micro.service.ApplicationFormMicroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.Period;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationFormMicroServiceImpl implements ApplicationFormMicroService {
    private final ApplicationFormMicroRepository applicationFormMicroRepository;
    private final ApplicationFormMicroSavingMapper applicationFormMicroSavingMapper;
    private final ApplicationFormMicroResponseMapper applicationFormMicroResponseMapper;
    private final ApplicationFormMicroInfoService applicationFormMicroInfoService;
    private final PageMapper pageMapper;
    private final ApplicationFormMicroLastVersionRepository applicationFormMicroLastVersionRepository;
    private final UserRepository userRepository;

    private static final String APPLICATION_FORM_MICRO_ERROR_MESSAGE = "ApplicationFormMicro with id %s doesn't exist";
    private static final String USER_ERROR_MESSAGE = "User with email %s doesn't exist";


    @Override
    @Transactional(readOnly = true)
    public GenericFormResponse<ApplicationFormMicroInfo, ApplicationFormMicroResponseDto> getApplicationFormMicro(UUID id, String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format(USER_ERROR_MESSAGE, email)));
        ApplicationFormMicro applicationFormMicro = applicationFormMicroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(APPLICATION_FORM_MICRO_ERROR_MESSAGE, id)));
        ApplicationFormMicroInfo applicationFormMicroInfo = applicationFormMicroInfoService.getApplicationFormMicroInfo();
        applicationFormMicroInfo.setStatuses(applicationFormMicroInfoService.getNextStatusesByCurrentStatus(applicationFormMicro.getStatus(), user.getRole()));
        //коли берем форму якщо роль адмін то повертаємо всі статуси, якщо оператор то тільки ті які йдуть після поточного
        return new GenericFormResponse<>(applicationFormMicroInfo,
                applicationFormMicroResponseMapper.toDto(applicationFormMicro));
    }

    @Override
    @Transactional(readOnly = true)
    public GenericFormResponse<ApplicationFormMicroInfo, PageResponse<ApplicationFormMicroResponseDto>> getAllApplicationFormsMicro(Pageable pageable) {
        Page<ApplicationFormMicroResponseDto> allApplicationFormsMicro = applicationFormMicroRepository.findAllNotDeleted(pageable)
                .map(applicationFormMicroResponseMapper::toDto);
        ApplicationFormMicroInfo applicationFormMicroInfo = applicationFormMicroInfoService.getApplicationFormMicroInfo();
        applicationFormMicroInfo.setStatuses(applicationFormMicroInfoService.getAllStatuses());
        //коли берем всі форми то повертаємо всі статуси
        return new GenericFormResponse<>(applicationFormMicroInfo,
                pageMapper.toPageResponse(allApplicationFormsMicro));
    }

    @Override
    @Transactional
    public ApplicationFormMicroResponseDto saveApplicationFormMicro(ApplicationFormMicroSavingDto applicationFormMicroSavingDto) {
        ApplicationFormMicro applicationFormMicro = applicationFormMicroSavingMapper.toEntity(applicationFormMicroSavingDto);
        applicationFormMicro.setDonor(MicroDonor.fromString(applicationFormMicroSavingDto.getDonorName()));
        applicationFormMicro.setAge(getYears(applicationFormMicro));
        applicationFormMicro.setStatus(MicroStatus.FORM_MICRO_NEW);
        return applicationFormMicroResponseMapper.toDto(applicationFormMicroRepository.save(applicationFormMicro));
    }

    private static int getYears(ApplicationFormMicro applicationFormMicro) {
        return Period.between(applicationFormMicro.getContactInfo().getDateOfBirth().toLocalDate(),
                OffsetDateTime.now().toLocalDate()).getYears();
    }

    @Transactional
    @Override
    public void deleteApplicationFormMicro(UUID id) {
        ApplicationFormMicro applicationFormMicro = applicationFormMicroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(APPLICATION_FORM_MICRO_ERROR_MESSAGE, id)));
        applicationFormMicro.setLastUpdate(OffsetDateTime.now());
        applicationFormMicro.setDeleted(true);
    }

    @Override
    @Transactional
    public void setAsNotDeletedApplicationFormMicro(UUID id) {
        ApplicationFormMicro applicationFormMicro = applicationFormMicroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(APPLICATION_FORM_MICRO_ERROR_MESSAGE, id)));
        applicationFormMicro.setLastUpdate(OffsetDateTime.now());
        applicationFormMicro.setDeleted(false);
    }

    @Transactional
    @Override
    public ApplicationFormMicroResponseDto updateApplicationFormMicro(ApplicationFormMicroUpdateDto applicationFormMicroUpdateDto, UUID id, String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format(USER_ERROR_MESSAGE, email)));
        MicroStatus status = MicroStatus.valueOf(applicationFormMicroUpdateDto.getStatus());
        ApplicationFormMicro applicationFormMicro = applicationFormMicroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(APPLICATION_FORM_MICRO_ERROR_MESSAGE, id)));

        if (applicationFormMicroUpdateDto.getStatus() != null) {
            if (applicationFormMicro.getStatus().getName().contains("Reject") && user.getRole().getName().equals("ROLE_OPERATOR")) {
                throw new PermissionException("You don't have permission to change status");
                //якщо поточний статус reject і роль оператор то не дозволяємо змінювати статус
            }
            if (user.getRole().getName().equals("ROLE_ADMIN")) {
                if (MicroStatus.getStatusesAfter(applicationFormMicro.getStatus()).contains(status) || status == MicroStatus.getPreviousStatus(applicationFormMicro.getStatus())) {
                    applicationFormMicro.setStatus(status);
                    //якщо роль admin і прийшов будь який статус який стоїть після поточного або той який перед поточним то міняємо
                } else {
                    throw new StatusException(String.format("You can't change status from %s to %s", applicationFormMicro.getStatus(), status));
                }
            }
            applicationFormMicro.setStatus(status); //якщо роль не адмін і поточний статус не reject то міняємо статус на той який прийшов
        }
        if (applicationFormMicro.getStatus() == MicroStatus.FORM_MICRO_APPROVED) {
            applicationFormMicro.setDateOfApproval(OffsetDateTime.now());
        }
        ApplicationFormMicro applicationFormMicroBeforeUpdate = new ApplicationFormMicro(applicationFormMicro);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAboutProgram()).ifPresent(applicationFormMicro::setAboutProgram);
        Optional.ofNullable(applicationFormMicroUpdateDto.getConflictDamages()).ifPresent(applicationFormMicro::setConflictDamages);
        Optional.ofNullable(applicationFormMicroUpdateDto.getGrandFunding()).ifPresent(applicationFormMicro::setGrandFunding);
        Optional.ofNullable(applicationFormMicroUpdateDto.getIsVpo()).ifPresent(applicationFormMicro::setVpo);
        Optional.ofNullable(applicationFormMicroUpdateDto.getVpoReferenceNumber()).ifPresent(applicationFormMicro::setVpoReferenceNumber);
        Optional.ofNullable(applicationFormMicroUpdateDto.getVpoReferenceIssuedDate()).ifPresent(applicationFormMicro::setVpoReferenceIssuedDate);
        Optional.ofNullable(applicationFormMicroUpdateDto.getHomeLeavingReasons()).ifPresent(applicationFormMicro::setHomeLeavingReasons);
        Optional.ofNullable(applicationFormMicroUpdateDto.getVpoReferenceNumber()).ifPresent(applicationFormMicro::setVpoReferenceNumber);
        Optional.ofNullable(applicationFormMicroUpdateDto.getPeopleLeavingWithYou()).ifPresent(applicationFormMicro::setPeopleLeavingWithYou);
        Optional.ofNullable(applicationFormMicroUpdateDto.getThreeMonthMovingPlans()).ifPresent(applicationFormMicro::setThreeMonthMovingPlans);
        Optional.ofNullable(applicationFormMicroUpdateDto.getFamilyAverageMonthlyIncome()).ifPresent(applicationFormMicro::setFamilyAverageMonthlyIncome);
        Optional.ofNullable(applicationFormMicroUpdateDto.getFamilyAverageMonthlyIncomeBeforeConflict()).ifPresent(applicationFormMicro::setFamilyAverageMonthlyIncomeBeforeConflict);
        Optional.ofNullable(applicationFormMicroUpdateDto.getVulnerabilities()).ifPresent(applicationFormMicro::setVulnerabilities);
        Optional.ofNullable(applicationFormMicroUpdateDto.getSelfSufficiency()).ifPresent(applicationFormMicro::setSelfSufficiency);
        Optional.ofNullable(applicationFormMicroUpdateDto.getNegativeSurvivalStrategies()).ifPresent(applicationFormMicro::setNegativeSurvivalStrategies);
        Optional.ofNullable(applicationFormMicroUpdateDto.getTookPartInSuchPrograms()).ifPresent(applicationFormMicro::setTookPartInSuchPrograms);

        Optional.ofNullable(applicationFormMicroUpdateDto.getContactInfo().getPhoneNumber()).ifPresent(applicationFormMicro.getContactInfo()::setPhoneNumber);
        Optional.ofNullable(applicationFormMicroUpdateDto.getContactInfo().getFirstName()).ifPresent(applicationFormMicro.getContactInfo()::setFirstName);
        Optional.ofNullable(applicationFormMicroUpdateDto.getContactInfo().getEmail()).ifPresent(applicationFormMicro.getContactInfo()::setEmail);
        Optional.ofNullable(applicationFormMicroUpdateDto.getContactInfo().getMiddleName()).ifPresent(applicationFormMicro.getContactInfo()::setMiddleName);
        Optional.ofNullable(applicationFormMicroUpdateDto.getContactInfo().getLastName()).ifPresent(applicationFormMicro.getContactInfo()::setLastName);
        Optional.ofNullable(applicationFormMicroUpdateDto.getContactInfo().getDateOfBirth()).ifPresent(applicationFormMicro.getContactInfo()::setDateOfBirth);
        Optional.ofNullable(applicationFormMicroUpdateDto.getContactInfo().getGender()).ifPresent(applicationFormMicro.getContactInfo()::setGender);
        Optional.ofNullable(applicationFormMicroUpdateDto.getContactInfo().getIpn()).ifPresent(applicationFormMicro.getContactInfo()::setIpn);

        Optional.ofNullable(applicationFormMicroUpdateDto.getDocumentInfo().getDocumentType()).ifPresent(applicationFormMicro.getDocumentInfo()::setDocumentType);
        Optional.ofNullable(applicationFormMicroUpdateDto.getDocumentInfo().getDocumentSeries()).ifPresent(applicationFormMicro.getDocumentInfo()::setDocumentSeries);
        Optional.ofNullable(applicationFormMicroUpdateDto.getDocumentInfo().getDocumentNumber()).ifPresent(applicationFormMicro.getDocumentInfo()::setDocumentNumber);
        Optional.ofNullable(applicationFormMicroUpdateDto.getDocumentInfo().getDocumentIssuedDate()).ifPresent(applicationFormMicro.getDocumentInfo()::setDocumentIssuedDate);
        Optional.ofNullable(applicationFormMicroUpdateDto.getDocumentInfo().getDocumentIssuedBy()).ifPresent(applicationFormMicro.getDocumentInfo()::setDocumentIssuedBy);

        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getRegion()).ifPresent(applicationFormMicro.getAddressInfo()::setRegion);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getDistrict()).ifPresent(applicationFormMicro.getAddressInfo()::setDistrict);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getCity()).ifPresent(applicationFormMicro.getAddressInfo()::setCity);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getStreetName()).ifPresent(applicationFormMicro.getAddressInfo()::setStreetName);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getStreetType()).ifPresent(applicationFormMicro.getAddressInfo()::setStreetType);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getHromada()).ifPresent(applicationFormMicro.getAddressInfo()::setHromada);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getApartmentNumber()).ifPresent(applicationFormMicro.getAddressInfo()::setApartmentNumber);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getBuildingNumber()).ifPresent(applicationFormMicro.getAddressInfo()::setBuildingNumber);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getPavilionNumber()).ifPresent(applicationFormMicro.getAddressInfo()::setPavilionNumber);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getRegionBeforeMoving()).ifPresent(applicationFormMicro.getAddressInfo()::setRegionBeforeMoving);
        Optional.ofNullable(applicationFormMicroUpdateDto.getAddressInfo().getFullAddressBeforeMoving()).ifPresent(applicationFormMicro.getAddressInfo()::setFullAddressBeforeMoving);

        Optional.ofNullable(applicationFormMicroUpdateDto.getBusinessInfo().getBusinessConsumers()).ifPresent(applicationFormMicro.getBusinessInfo()::setBusinessConsumers);
        Optional.ofNullable(applicationFormMicroUpdateDto.getBusinessInfo().getBusinessManufacturingPlace()).ifPresent(applicationFormMicro.getBusinessInfo()::setBusinessManufacturingPlace);
        Optional.ofNullable(applicationFormMicroUpdateDto.getBusinessInfo().getBusinessManufacturingWay()).ifPresent(applicationFormMicro.getBusinessInfo()::setBusinessManufacturingWay);
        Optional.ofNullable(applicationFormMicroUpdateDto.getBusinessInfo().getBusinessMonthlyIncome()).ifPresent(applicationFormMicro.getBusinessInfo()::setBusinessMonthlyIncome);
        Optional.ofNullable(applicationFormMicroUpdateDto.getBusinessInfo().getShortBusinessIdeaDescription()).ifPresent(applicationFormMicro.getBusinessInfo()::setShortBusinessIdeaDescription);
        Optional.ofNullable(applicationFormMicroUpdateDto.getBusinessInfo().getNeededGrantAmount()).ifPresent(applicationFormMicro.getBusinessInfo()::setNeededGrantAmount);
        Optional.ofNullable(applicationFormMicroUpdateDto.getBusinessInfo().getGrantExpenses()).ifPresent(applicationFormMicro.getBusinessInfo()::setGrantExpenses);
        Optional.ofNullable(applicationFormMicroUpdateDto.getBusinessInfo().getInvestedMoneyAmount()).ifPresent(applicationFormMicro.getBusinessInfo()::setInvestedMoneyAmount);
        Optional.ofNullable(applicationFormMicroUpdateDto.getBusinessInfo().getEmployeesHave()).ifPresent(applicationFormMicro.getBusinessInfo()::setEmployeesHave);
        Optional.ofNullable(applicationFormMicroUpdateDto.getBusinessInfo().getNumberOfEmployees()).ifPresent(applicationFormMicro.getBusinessInfo()::setNumberOfEmployees);

        Optional.ofNullable(applicationFormMicroUpdateDto.getStatusDescription()).ifPresent(applicationFormMicro::setStatusDescription);
        Optional.ofNullable(applicationFormMicroUpdateDto.getExperienceOfSuchActivities()).ifPresent(applicationFormMicro::setExperienceOfSuchActivities);

        Optional.ofNullable(applicationFormMicroUpdateDto.getDonor())
                .ifPresent(donor -> applicationFormMicro.setDonor(MicroDonor.fromString(donor)));
        Optional.ofNullable(applicationFormMicroUpdateDto.getDateOfMonitoring()).ifPresent(applicationFormMicro::setDateOfMonitoring);
        Optional.ofNullable(applicationFormMicroUpdateDto.getDateOfFunding()).ifPresent(applicationFormMicro::setDateOfFunding);

        applicationFormMicro.setLastUpdate(OffsetDateTime.now());
        ApplicationFormMicro updatedApplicationFormMicro = applicationFormMicroRepository.save(applicationFormMicro);
        throwFormUpdatedEvent();
        saveLastApplicationFormMicroVersion(applicationFormMicroBeforeUpdate);
        return applicationFormMicroResponseMapper.toDto(updatedApplicationFormMicro);
    }

    private void throwFormUpdatedEvent() {//mock
    }

    public void saveLastApplicationFormMicroVersion(ApplicationFormMicro applicationFormMicro) {
        Optional<ApplicationFormMicroLastVersion> optionalMainFormLastVersion = applicationFormMicroLastVersionRepository.findByApplicationFormMicroId(applicationFormMicro.getId());
        if (optionalMainFormLastVersion.isEmpty()) {
            ApplicationFormMicroLastVersion applicationFormMicroLastVersion = new ApplicationFormMicroLastVersion();
            applicationFormMicroLastVersion.setApplicationFormMicro(applicationFormMicro);
            applicationFormMicroLastVersionRepository.save(applicationFormMicroLastVersion);
            return;
        }
        ApplicationFormMicroLastVersion applicationFormMicroLastVersion = optionalMainFormLastVersion.orElseThrow(EntityNotFoundException::new);
        applicationFormMicroLastVersion.setApplicationFormMicro(applicationFormMicro);
        applicationFormMicroLastVersionRepository.save(applicationFormMicroLastVersion);
    }

    @Override
    @Transactional
    public ApplicationFormMicroResponseDto revertApplicationFormMicroToLastVersion(UUID id) {
        ApplicationFormMicroLastVersion applicationFormMicroLastVersion = applicationFormMicroLastVersionRepository.findByApplicationFormMicroId(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("ApplicationFormMicro with id %s doesn't have last version", id)));
        ApplicationFormMicro applicationFormMicro = applicationFormMicroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(APPLICATION_FORM_MICRO_ERROR_MESSAGE, id)));
        applicationFormMicro.revertToLastVersion(applicationFormMicroLastVersion);
        applicationFormMicro.setLastUpdate(OffsetDateTime.now());
        return applicationFormMicroResponseMapper.toDto(applicationFormMicro);
    }

    @Override
    @Transactional
    public ApplicationFormMicroResponseDto setLawyerStatusForApplicationFormMicro(UUID id, LawyerStatusRequest lawyerStatusRequest) {
        ApplicationFormMicro applicationFormMicro = applicationFormMicroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(APPLICATION_FORM_MICRO_ERROR_MESSAGE, id)));
        applicationFormMicro.setLawyerStatus(lawyerStatusRequest.isStatus());
        applicationFormMicro.setLastUpdate(OffsetDateTime.now());
        ApplicationFormMicro savedForm = applicationFormMicroRepository.save(applicationFormMicro);
        return applicationFormMicroResponseMapper.toDto(savedForm);
    }
}
