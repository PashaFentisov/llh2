package ngo.drc.references.service.impl;

import ngo.drc.references.bundle.domain.AboutProgram;
import ngo.drc.references.bundle.domain.ConflictDamage;
import ngo.drc.references.bundle.domain.FirstFormPart;
import ngo.drc.references.bundle.dto.BundleRow;
import ngo.drc.references.bundle.locale.LocaleContextHolder;
import ngo.drc.references.form.FirstForm;
import ngo.drc.references.service.FirstFormService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class FirstFormServiceImpl implements FirstFormService {
    private ResourceBundle exampleBundle;
    @Override
    public FirstForm getFirstFormInfo() {
        exampleBundle = ResourceBundle.getBundle("reference_book",
                Locale.forLanguageTag(LocaleContextHolder.getLocale()));
        return FirstForm.builder()
                .aboutProgram(buildT(new AboutProgram(), "about.program.", "about.program.label", "about.program.is-multiple"))
                .conflictDamage()
                .gender()
                .grandFunding()
                .homeLeavingReason()
                .negativeSurvivalStrategy()
                .personDocumentType()
                .selfSufficiency()
                .streetType()
                .vulnerability()
                .build();
    }

//    public ConflictDamage buildConflictDamage(){
//        ConflictDamage conflictDamage = new ConflictDamage();
//        List<BundleRow> list = exampleBundle.keySet()
//                .stream()
//                .filter(key -> key.startsWith("conflict.damage."))
//                .map(key -> new BundleRow(key, exampleBundle.getString(key)))
//                .toList();
//        conflictDamage.setOptions(list);
//        conflictDamage.setLabel(exampleBundle.getString("conflict.label"));
//        conflictDamage.setMultiple(Boolean.parseBoolean(exampleBundle.getString("conflict.is-multiple")));
//        return conflictDamage;
//    }

    public <T extends FirstFormPart> T buildT(T t, String keyStartsWith, String label, String isMultiple){
        List<BundleRow> list = exampleBundle.keySet()
                .stream()
                .filter(key -> key.startsWith(keyStartsWith))
                .map(key -> new BundleRow(key, exampleBundle.getString(key)))
                .toList();
        t.setOptions(list);
        t.setLabel(exampleBundle.getString(label));
        t.setMultiple(Boolean.parseBoolean(exampleBundle.getString(isMultiple)));
        return t;
    }
}
