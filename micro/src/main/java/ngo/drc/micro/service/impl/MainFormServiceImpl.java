package ngo.drc.micro.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ngo.drc.core.dto.GenericFormResponse;
import ngo.drc.core.dto.GenericPageFormResponse;
import ngo.drc.endpoint.PageResponse;
import ngo.drc.endpoint.mapper.PageMapper;
import ngo.drc.micro.dto.MainFormResponseDto;
import ngo.drc.micro.dto.MainFormSavingDto;
import ngo.drc.micro.dto.MainFormUpdateDto;
import ngo.drc.micro.entity.MainForm;
import ngo.drc.micro.form.MainFormInfo;
import ngo.drc.micro.mapper.MainFormResponseMapper;
import ngo.drc.micro.mapper.MainFormSavingMapper;
import ngo.drc.micro.repository.MainFormRepository;
import ngo.drc.micro.service.MainFormInfoService;
import ngo.drc.micro.service.MainFormService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MainFormServiceImpl implements MainFormService {
    private final MainFormRepository mainFormRepository;
    private final MainFormSavingMapper mainFormSavingMapper;
    private final MainFormResponseMapper mainFormResponseMapper;
    private final MainFormInfoService mainFormInfoService;
    private final PageMapper pageMapper;

    private static final String ERROR_MESSAGE = "MainForm with id %s doesn't exist";


    @Override
    @Transactional(readOnly = true)
    public GenericFormResponse<MainFormInfo, MainFormResponseDto> getMainForm(Long id) {
        MainForm mainForm = mainFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MESSAGE, id)));
        return new GenericFormResponse<>(mainFormInfoService.getMainFormInfo(),
                mainFormResponseMapper.toDto(mainForm));
    }

    @Override
    @Transactional(readOnly = true)
    public GenericPageFormResponse<MainFormInfo, PageResponse<MainFormResponseDto>> getAllMainForms(Pageable pageable) {
        return new GenericPageFormResponse<>(mainFormInfoService.getMainFormInfo(),
                pageMapper.toPageResponse(mainFormRepository.findAll(pageable).map(mainFormResponseMapper::toDto)));
    }

    @Override
    @Transactional
    public MainFormResponseDto saveMicroMainForm(MainFormSavingDto mainFormSavingDto) {
        MainForm entity = mainFormSavingMapper.toEntity(mainFormSavingDto);
        return mainFormResponseMapper.toDto(mainFormRepository.save(entity));
    }

    @Transactional
    public void deleteMicroMainForm(Long id) {
        MainForm mainForm = mainFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MESSAGE, id)));
        mainForm.setDeleted(true);
    }

    @Transactional
    public MainFormResponseDto updateMicroMainForm(MainFormUpdateDto mainFormUpdateDto, Long id) {
        MainForm mainForm = mainFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MESSAGE, id)));
        //todo update all fields
//        Optional.ofNullable(actorDto.getBiography()).ifPresent(actor::setBiography);
        MainForm updatedMainForm = mainFormRepository.save(mainForm);
        throwFormUpdatedEvent();
        saveLastMainFormVersion();
        return mainFormResponseMapper.toDto(updatedMainForm);
    }

    private void throwFormUpdatedEvent() {
    }

    private void saveLastMainFormVersion() {
    }
}
