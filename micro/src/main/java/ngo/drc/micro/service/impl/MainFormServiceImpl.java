package ngo.drc.micro.service.impl;

import lombok.RequiredArgsConstructor;
import ngo.drc.micro.dto.MainFormSavingDto;
import ngo.drc.micro.entity.MainForm;
import ngo.drc.micro.mapper.MainFormSavingMapper;
import ngo.drc.micro.repository.MainFormRepository;
import ngo.drc.micro.service.MainFormService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MainFormServiceImpl implements MainFormService {
    private final MainFormRepository mainFormRepository;
    private final MainFormSavingMapper mainFormSavingMapper;


    @Override
    @Transactional
    public void saveMicroMainFormInfo(MainFormSavingDto mainFormSavingDto) {
        MainForm entity = mainFormSavingMapper.toEntity(mainFormSavingDto);
        mainFormRepository.save(entity);
    }
}
