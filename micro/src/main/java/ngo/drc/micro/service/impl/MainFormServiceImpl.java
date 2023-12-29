package ngo.drc.micro.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ngo.drc.core.dto.GenericFormResponse;
import ngo.drc.core.endpoint.PageResponse;
import ngo.drc.core.endpoint.mapper.PageMapper;
import ngo.drc.core.security.entity.User;
import ngo.drc.core.security.repository.UserRepository;
import ngo.drc.micro.dto.MainFormResponseDto;
import ngo.drc.micro.dto.MainFormSavingDto;
import ngo.drc.micro.dto.MainFormUpdateDto;
import ngo.drc.micro.entity.MainForm;
import ngo.drc.micro.entity.MainFormLastVersion;
import ngo.drc.micro.enumeration.MicroStatus;
import ngo.drc.micro.form.MainFormInfo;
import ngo.drc.micro.mapper.MainFormResponseMapper;
import ngo.drc.micro.mapper.MainFormSavingMapper;
import ngo.drc.micro.repository.MainFormLastVersionRepository;
import ngo.drc.micro.repository.MainFormRepository;
import ngo.drc.micro.service.MainFormInfoService;
import ngo.drc.micro.service.MainFormService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MainFormServiceImpl implements MainFormService {
    private final MainFormRepository mainFormRepository;
    private final MainFormSavingMapper mainFormSavingMapper;
    private final MainFormResponseMapper mainFormResponseMapper;
    private final MainFormInfoService mainFormInfoService;
    private final PageMapper pageMapper;
    private final MainFormLastVersionRepository mainFormLastVersionRepository;
    private final UserRepository userRepository;

    private static final String MAIN_FORM_ERROR_MESSAGE = "MainForm with id %s doesn't exist";


    @Override
    @Transactional(readOnly = true)
    public GenericFormResponse<MainFormInfo, MainFormResponseDto> getMainForm(UUID id, String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with email %s doesn't exist", email)));
        MainForm mainForm = mainFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MAIN_FORM_ERROR_MESSAGE, id)));
        MainFormInfo mainFormInfo = mainFormInfoService.getMainFormInfo();
        mainFormInfo.setStatuses(mainFormInfoService.getNextStatusesByCurrentStatus(mainForm.getStatus(), user.getRole())); //todo чи треба повертати також теперішній
        return new GenericFormResponse<>(mainFormInfo,
                mainFormResponseMapper.toDto(mainForm));
    }

    @Override
    @Transactional(readOnly = true)
    public GenericFormResponse<MainFormInfo, PageResponse<MainFormResponseDto>> getAllMainForms(Pageable pageable) {
        Page<MainFormResponseDto> allMainForms = mainFormRepository.findAllNotDeleted(pageable).map(mainFormResponseMapper::toDto);
        MainFormInfo mainFormInfo = mainFormInfoService.getMainFormInfo();
        mainFormInfo.setStatuses(mainFormInfoService.getAllStatuses());
        return new GenericFormResponse<>(mainFormInfo,
                pageMapper.toPageResponse(allMainForms));
    }

    @Override
    @Transactional
    public MainFormResponseDto saveMicroMainForm(MainFormSavingDto mainFormSavingDto) {
        MainForm mainForm = mainFormSavingMapper.toEntity(mainFormSavingDto);
        mainForm.setStatus(MicroStatus.FORM_MICRO_NEW);
        return mainFormResponseMapper.toDto(mainFormRepository.save(mainForm));
    }

    @Transactional
    @Override
    public void deleteMicroMainForm(UUID id) {
        MainForm mainForm = mainFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MAIN_FORM_ERROR_MESSAGE, id)));
        mainForm.setDeleted(true);
    }

    @Override
    @Transactional
    public void setAsNotDeletedMainForm(UUID id) {
        MainForm mainForm = mainFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MAIN_FORM_ERROR_MESSAGE, id)));
        mainForm.setDeleted(false);
    }

    @Transactional
    @Override
    public MainFormResponseDto updateMicroMainForm(MainFormUpdateDto mainFormUpdateDto, UUID id) {
        //todo якщо статус reject і немає права на зміну reject то кидаєм exception
        //todo якщо статус reject і є право на зміну reject то змінюємо статус на той який прийшов
        //todo перевіряти чи не перескакуєм на інші статуси
        MainForm mainForm = mainFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MAIN_FORM_ERROR_MESSAGE, id)));
        MainForm mainFormBeforeUpdate = new MainForm(mainForm);
        Optional.ofNullable(mainFormUpdateDto.getAboutProgram()).ifPresent(mainForm::setAboutProgram);
        Optional.ofNullable(mainFormUpdateDto.getConflictDamages()).ifPresent(mainForm::setConflictDamages);
        Optional.ofNullable(mainFormUpdateDto.getGrandFunding()).ifPresent(mainForm::setGrandFunding);
        Optional.ofNullable(mainFormUpdateDto.getIsVpo()).ifPresent(mainForm::setVpo);
        Optional.ofNullable(mainFormUpdateDto.getVpoReferenceNumber()).ifPresent(mainForm::setVpoReferenceNumber);
        Optional.ofNullable(mainFormUpdateDto.getVpoReferenceIssuedDate()).ifPresent(mainForm::setVpoReferenceIssuedDate);
        Optional.ofNullable(mainFormUpdateDto.getHomeLeavingReasons()).ifPresent(mainForm::setHomeLeavingReasons);
        Optional.ofNullable(mainFormUpdateDto.getVpoReferenceNumber()).ifPresent(mainForm::setVpoReferenceNumber);
        Optional.ofNullable(mainFormUpdateDto.getPeopleLeavingWithYou()).ifPresent(mainForm::setPeopleLeavingWithYou);
        Optional.ofNullable(mainFormUpdateDto.getThreeMonthMovingPlans()).ifPresent(mainForm::setThreeMonthMovingPlans);
        Optional.ofNullable(mainFormUpdateDto.getFamilyAverageMonthlyIncome()).ifPresent(mainForm::setFamilyAverageMonthlyIncome);
        Optional.ofNullable(mainFormUpdateDto.getFamilyAverageMonthlyIncomeBeforeConflict()).ifPresent(mainForm::setFamilyAverageMonthlyIncomeBeforeConflict);
        Optional.ofNullable(mainFormUpdateDto.getVulnerabilities()).ifPresent(mainForm::setVulnerabilities);
        Optional.ofNullable(mainFormUpdateDto.getSelfSufficiency()).ifPresent(mainForm::setSelfSufficiency);
        Optional.ofNullable(mainFormUpdateDto.getNegativeSurvivalStrategies()).ifPresent(mainForm::setNegativeSurvivalStrategies);
        Optional.ofNullable(mainFormUpdateDto.getTookPartInSuchPrograms()).ifPresent(mainForm::setTookPartInSuchPrograms);

        Optional.ofNullable(mainFormUpdateDto.getContactInfo().getPhoneNumber()).ifPresent(mainForm.getContactInfo()::setPhoneNumber);
        Optional.ofNullable(mainFormUpdateDto.getContactInfo().getFirstName()).ifPresent(mainForm.getContactInfo()::setFirstName);
        Optional.ofNullable(mainFormUpdateDto.getContactInfo().getEmail()).ifPresent(mainForm.getContactInfo()::setEmail);
        Optional.ofNullable(mainFormUpdateDto.getContactInfo().getMiddleName()).ifPresent(mainForm.getContactInfo()::setMiddleName);
        Optional.ofNullable(mainFormUpdateDto.getContactInfo().getLastName()).ifPresent(mainForm.getContactInfo()::setLastName);
        Optional.ofNullable(mainFormUpdateDto.getContactInfo().getDateOfBirth()).ifPresent(mainForm.getContactInfo()::setDateOfBirth);
        Optional.ofNullable(mainFormUpdateDto.getContactInfo().getGender()).ifPresent(mainForm.getContactInfo()::setGender);
        Optional.ofNullable(mainFormUpdateDto.getContactInfo().getIpn()).ifPresent(mainForm.getContactInfo()::setIpn);

        Optional.ofNullable(mainFormUpdateDto.getDocumentInfo().getDocumentType()).ifPresent(mainForm.getDocumentInfo()::setDocumentType);
        Optional.ofNullable(mainFormUpdateDto.getDocumentInfo().getDocumentSeries()).ifPresent(mainForm.getDocumentInfo()::setDocumentSeries);
        Optional.ofNullable(mainFormUpdateDto.getDocumentInfo().getDocumentNumber()).ifPresent(mainForm.getDocumentInfo()::setDocumentNumber);
        Optional.ofNullable(mainFormUpdateDto.getDocumentInfo().getDocumentIssuedDate()).ifPresent(mainForm.getDocumentInfo()::setDocumentIssuedDate);
        Optional.ofNullable(mainFormUpdateDto.getDocumentInfo().getDocumentIssuedBy()).ifPresent(mainForm.getDocumentInfo()::setDocumentIssuedBy);

        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getRegion()).ifPresent(mainForm.getAddressInfo()::setRegion);
        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getDistrict()).ifPresent(mainForm.getAddressInfo()::setDistrict);
        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getCity()).ifPresent(mainForm.getAddressInfo()::setCity);
        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getStreetName()).ifPresent(mainForm.getAddressInfo()::setStreetName);
        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getStreetType()).ifPresent(mainForm.getAddressInfo()::setStreetType);
        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getHromada()).ifPresent(mainForm.getAddressInfo()::setHromada);
        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getApartmentNumber()).ifPresent(mainForm.getAddressInfo()::setApartmentNumber);
        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getBuildingNumber()).ifPresent(mainForm.getAddressInfo()::setBuildingNumber);
        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getPavilionNumber()).ifPresent(mainForm.getAddressInfo()::setPavilionNumber);
        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getRegionBeforeMoving()).ifPresent(mainForm.getAddressInfo()::setRegionBeforeMoving);
        Optional.ofNullable(mainFormUpdateDto.getAddressInfo().getFullAddressBeforeMoving()).ifPresent(mainForm.getAddressInfo()::setFullAddressBeforeMoving);

        Optional.ofNullable(mainFormUpdateDto.getBusinessInfo().getBusinessConsumers()).ifPresent(mainForm.getBusinessInfo()::setBusinessConsumers);
        Optional.ofNullable(mainFormUpdateDto.getBusinessInfo().getBusinessManufacturingPlace()).ifPresent(mainForm.getBusinessInfo()::setBusinessManufacturingPlace);
        Optional.ofNullable(mainFormUpdateDto.getBusinessInfo().getBusinessManufacturingWay()).ifPresent(mainForm.getBusinessInfo()::setBusinessManufacturingWay);
        Optional.ofNullable(mainFormUpdateDto.getBusinessInfo().getBusinessMonthlyIncome()).ifPresent(mainForm.getBusinessInfo()::setBusinessMonthlyIncome);
        Optional.ofNullable(mainFormUpdateDto.getBusinessInfo().getShortBusinessIdeaDescription()).ifPresent(mainForm.getBusinessInfo()::setShortBusinessIdeaDescription);
        Optional.ofNullable(mainFormUpdateDto.getBusinessInfo().getNeededGrantAmount()).ifPresent(mainForm.getBusinessInfo()::setNeededGrantAmount);
        Optional.ofNullable(mainFormUpdateDto.getBusinessInfo().getGrantExpenses()).ifPresent(mainForm.getBusinessInfo()::setGrantExpenses);
        Optional.ofNullable(mainFormUpdateDto.getBusinessInfo().getInvestedMoneyAmount()).ifPresent(mainForm.getBusinessInfo()::setInvestedMoneyAmount);
        Optional.ofNullable(mainFormUpdateDto.getBusinessInfo().getEmployeesHave()).ifPresent(mainForm.getBusinessInfo()::setEmployeesHave);
        Optional.ofNullable(mainFormUpdateDto.getBusinessInfo().getNumberOfEmployees()).ifPresent(mainForm.getBusinessInfo()::setNumberOfEmployees);

        MainForm updatedMainForm = mainFormRepository.save(mainForm);
        throwFormUpdatedEvent();
        saveLastMainFormVersion(mainFormBeforeUpdate);
        return mainFormResponseMapper.toDto(updatedMainForm);
    }

    private void throwFormUpdatedEvent() {//mock
    }

    public void saveLastMainFormVersion(MainForm mainForm) {
        Optional<MainFormLastVersion> optionalMainFormLastVersion = mainFormLastVersionRepository.findByMainFormId(mainForm.getId());
        if (optionalMainFormLastVersion.isEmpty()) {
            MainFormLastVersion mainFormLastVersion = new MainFormLastVersion();
            mainFormLastVersion.setMainForm(mainForm);
            mainFormLastVersionRepository.save(mainFormLastVersion);
            return;
        }
        MainFormLastVersion mainFormLastVersion = optionalMainFormLastVersion.orElseThrow(EntityNotFoundException::new);
        mainFormLastVersion.setMainForm(mainForm);
        mainFormLastVersionRepository.save(mainFormLastVersion);
    }

    @Override
    @Transactional
    public MainFormResponseDto revertMainFormToLastVersion(UUID id) {
        MainFormLastVersion mainFormLastVersion = mainFormLastVersionRepository.findByMainFormId(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("MainForm with id %s doesn't have last version", id)));
        MainForm mainForm = mainFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MAIN_FORM_ERROR_MESSAGE, id)));
        mainForm.revertToLastVersion(mainFormLastVersion);
        return mainFormResponseMapper.toDto(mainForm);
    }
}
