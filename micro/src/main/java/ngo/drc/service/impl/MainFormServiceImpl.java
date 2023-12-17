package ngo.drc.service.impl;

import lombok.RequiredArgsConstructor;
import ngo.drc.MainFormSavingMapper;
import ngo.drc.bundle.domain.*;
import ngo.drc.bundle.dto.BundleRow;
import ngo.drc.bundle.form.MicroMainForm;
import ngo.drc.bundle.locale.LocaleContextHolder;
import ngo.drc.dto.MainFormSavingDto;
import ngo.drc.entity.MainForm;
import ngo.drc.repository.MainFormRepository;
import ngo.drc.service.MainFormService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class MainFormServiceImpl implements MainFormService {
    private ResourceBundle exampleBundle;
    private final MainFormRepository mainFormRepository;
    private final MainFormSavingMapper mainFormSavingMapper;

    @Override
    public MicroMainForm getMainFormInfo() {
        exampleBundle = ResourceBundle.getBundle("reference_book",
                Locale.forLanguageTag(LocaleContextHolder.getLocale()));
        return MicroMainForm.builder()
                .aboutProgram(buildMainFormComponent(new AboutProgram(), "about.program.", "label.about.program", "is-multiple.about.program"))
                .conflictDamage(buildMainFormComponent(new ConflictDamage(), "conflict.damage.", "label.conflict.damage", "is-multiple.conflict.damage"))
                .gender(buildMainFormComponent(new Gender(), "gender.", "label.gender", "is-multiple.gender"))
                .grandFunding(buildMainFormComponent(new GrandFunding(), "grand.funding.", "label.grand.funding", "is-multiple.grand.funding"))
                .homeLeavingReason(buildMainFormComponent(new HomeLeavingReason(), "home.leaving.reason.", "label.home.leaving.reason", "is-multiple.home.leaving.reason"))
                .negativeSurvivalStrategy(buildMainFormComponent(new NegativeSurvivalStrategy(), "negative.survival.strategy.", "label.negative.survival.strategy", "is-multiple.negative.survival.strategy"))
                .personDocumentType(buildMainFormComponent(new PersonDocumentType(), "person.document.type.", "label.person.document.type", "is-multiple.person.document.type"))
                .selfSufficiency(buildMainFormComponent(new SelfSufficiency(), "self.sufficiency.", "label.self.sufficiency", "is-multiple.self.sufficiency"))
                .streetType(buildMainFormComponent(new StreetType(), "street.type.", "label.street.type", "is-multiple.street.type"))
                .vulnerability(buildMainFormComponent(new Vulnerability(), "vulnerability.", "label.vulnerability", "is-multiple.vulnerability"))
                .build();
    }

    @Override
    @Transactional
    public void saveMicroMainFormInfo(MainFormSavingDto mainFormSavingDto) {
        MainForm entity = mainFormSavingMapper.toEntity(mainFormSavingDto);
        mainFormRepository.save(entity);
    }


    public <T extends MicroMainFormPart> T buildMainFormComponent(T componentObj, String keyStartsWith, String label, String isMultiple) {
        List<BundleRow> list = exampleBundle.keySet()
                .stream()
                .filter(key -> key.startsWith(keyStartsWith))
                .map(key -> new BundleRow(key, exampleBundle.getString(key)))
                .toList();
        componentObj.setOptions(list);
        componentObj.setLabel(exampleBundle.getString(label));
        componentObj.setMultiple(Boolean.parseBoolean(exampleBundle.getString(isMultiple)));
        return componentObj;
    }
}
