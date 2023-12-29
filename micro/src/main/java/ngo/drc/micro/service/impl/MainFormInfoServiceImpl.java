package ngo.drc.micro.service.impl;

import lombok.RequiredArgsConstructor;
import ngo.drc.core.security.entity.Role;
import ngo.drc.locale.LocaleContextHolder;
import ngo.drc.micro.dto.*;
import ngo.drc.micro.enumeration.MicroStatus;
import ngo.drc.micro.enumeration.MicroStatusUser;
import ngo.drc.micro.form.MainFormInfo;
import ngo.drc.micro.service.MainFormInfoService;
import ngo.drc.micro.util.MainFormInfoUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MainFormInfoServiceImpl implements MainFormInfoService {
    private ResourceBundle exampleBundle;


    public MainFormInfo getMainFormInfo() {
        exampleBundle = ResourceBundle.getBundle(MainFormInfoUtil.BUNDLE_NAME,
                Locale.forLanguageTag(LocaleContextHolder.getLocale()));
        return MainFormInfo.builder()
                .aboutProgram(buildMainFormComponent(new AboutProgram(), MainFormInfoUtil.ABOUT_PROGRAM_KEY, MainFormInfoUtil.ABOUT_PROGRAM_LABEL, MainFormInfoUtil.ABOUT_PROGRAM_IS_MULTIPLE))
                .conflictDamage(buildMainFormComponent(new ConflictDamage(), MainFormInfoUtil.CONFLICT_DAMAGE_KEY, MainFormInfoUtil.CONFLICT_DAMAGE_LABEL, MainFormInfoUtil.CONFLICT_DAMAGE_IS_MULTIPLE))
                .gender(buildMainFormComponent(new Gender(), MainFormInfoUtil.GENDER_KEY, MainFormInfoUtil.GENDER_LABEL, MainFormInfoUtil.GENDER_IS_MULTIPLE))
                .grandFunding(buildMainFormComponent(new GrandFunding(), MainFormInfoUtil.GRAND_FUNDING_KEY, MainFormInfoUtil.GRAND_FUNDING_LABEL, MainFormInfoUtil.GRAND_FUNDING_IS_MULTIPLE))
                .homeLeavingReason(buildMainFormComponent(new HomeLeavingReason(), MainFormInfoUtil.HOME_LEAVING_REASON_KEY, MainFormInfoUtil.HOME_LEAVING_REASON_LABEL, MainFormInfoUtil.HOME_LEAVING_REASON_IS_MULTIPLE))
                .negativeSurvivalStrategy(buildMainFormComponent(new NegativeSurvivalStrategy(), MainFormInfoUtil.NEGATIVE_SURVIVAL_STRATEGY_KEY, MainFormInfoUtil.NEGATIVE_SURVIVAL_STRATEGY_LABEL, MainFormInfoUtil.NEGATIVE_SURVIVAL_STRATEGY_IS_MULTIPLE))
                .personDocumentType(buildMainFormComponent(new PersonDocumentType(), MainFormInfoUtil.PERSON_DOCUMENT_KEY, MainFormInfoUtil.PERSON_DOCUMENT_LABEL, MainFormInfoUtil.PERSON_DOCUMENT_IS_MULTIPLE))
                .selfSufficiency(buildMainFormComponent(new SelfSufficiency(), MainFormInfoUtil.SELF_SUFFICIENCY_KEY, MainFormInfoUtil.SELF_SUFFICIENCY_LABEL, MainFormInfoUtil.SELF_SUFFICIENCY_IS_MULTIPLE))
                .streetType(buildMainFormComponent(new StreetType(), MainFormInfoUtil.STREET_TYPE_KEY, MainFormInfoUtil.STREET_TYPE_LABEL, MainFormInfoUtil.STREET_TYPE_IS_MULTIPLE))
                .vulnerability(buildMainFormComponent(new Vulnerability(), MainFormInfoUtil.VULNERABILITY_KEY, MainFormInfoUtil.VULNERABILITY_LABEL, MainFormInfoUtil.VULNERABILITY_IS_MULTIPLE))
                .statuses(getAllStatuses())
                .build();
    }

    @Override
    public Map<String, String> getAllStatuses() {
        Map<String, String> map = new HashMap<>();
        Arrays.stream(MicroStatus.values()).forEach(status -> map.put(status.toString(), status.getName()));
        return map;
    }

    @Override
    public Map<String, String> getNextStatusesByCurrentStatus(MicroStatus currentStatus, Role role) {
        switch (role.getName()) {
            case "ROLE_ADMIN":
                return getAllStatuses();
            case "ROLE_OPERATOR":
                Map<String, String> map = new HashMap<>();
                MicroStatusUser.getNextStatuses(currentStatus)
                        .forEach(status -> map.put(status.toString(), status.getName()));
                return map; //todo refactor
            default:
                throw new IllegalArgumentException("Unsupported role: " + role.getName());
        }
    }

    private <T extends MicroMainFormPart> T buildMainFormComponent(T componentObj, String keyStartsWith, String label, String isMultiple) {
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
