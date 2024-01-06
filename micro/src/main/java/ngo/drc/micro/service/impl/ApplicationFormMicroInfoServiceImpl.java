package ngo.drc.micro.service.impl;

import lombok.RequiredArgsConstructor;
import ngo.drc.core.security.entity.Role;
import ngo.drc.locale.LocaleContextHolder;
import ngo.drc.micro.dto.*;
import ngo.drc.micro.enumeration.MicroStatus;
import ngo.drc.micro.enumeration.MicroStatusOperator;
import ngo.drc.micro.form.ApplicationFormMicroInfo;
import ngo.drc.micro.service.ApplicationFormMicroInfoService;
import ngo.drc.micro.util.ApplicationFormMicroInfoUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationFormMicroInfoServiceImpl implements ApplicationFormMicroInfoService {
    private ResourceBundle exampleBundle;


    public ApplicationFormMicroInfo getApplicationFormMicroInfo() {
        exampleBundle = ResourceBundle.getBundle(ApplicationFormMicroInfoUtil.BUNDLE_NAME,
                Locale.forLanguageTag(LocaleContextHolder.getLocale()));
        return ApplicationFormMicroInfo.builder()
                .aboutProgram(buildMainFormComponent(new AboutProgram(), ApplicationFormMicroInfoUtil.ABOUT_PROGRAM_KEY, ApplicationFormMicroInfoUtil.ABOUT_PROGRAM_LABEL, ApplicationFormMicroInfoUtil.ABOUT_PROGRAM_IS_MULTIPLE))
                .conflictDamage(buildMainFormComponent(new ConflictDamage(), ApplicationFormMicroInfoUtil.CONFLICT_DAMAGE_KEY, ApplicationFormMicroInfoUtil.CONFLICT_DAMAGE_LABEL, ApplicationFormMicroInfoUtil.CONFLICT_DAMAGE_IS_MULTIPLE))
                .gender(buildMainFormComponent(new Gender(), ApplicationFormMicroInfoUtil.GENDER_KEY, ApplicationFormMicroInfoUtil.GENDER_LABEL, ApplicationFormMicroInfoUtil.GENDER_IS_MULTIPLE))
                .grandFunding(buildMainFormComponent(new GrandFunding(), ApplicationFormMicroInfoUtil.GRAND_FUNDING_KEY, ApplicationFormMicroInfoUtil.GRAND_FUNDING_LABEL, ApplicationFormMicroInfoUtil.GRAND_FUNDING_IS_MULTIPLE))
                .homeLeavingReason(buildMainFormComponent(new HomeLeavingReason(), ApplicationFormMicroInfoUtil.HOME_LEAVING_REASON_KEY, ApplicationFormMicroInfoUtil.HOME_LEAVING_REASON_LABEL, ApplicationFormMicroInfoUtil.HOME_LEAVING_REASON_IS_MULTIPLE))
                .negativeSurvivalStrategy(buildMainFormComponent(new NegativeSurvivalStrategy(), ApplicationFormMicroInfoUtil.NEGATIVE_SURVIVAL_STRATEGY_KEY, ApplicationFormMicroInfoUtil.NEGATIVE_SURVIVAL_STRATEGY_LABEL, ApplicationFormMicroInfoUtil.NEGATIVE_SURVIVAL_STRATEGY_IS_MULTIPLE))
                .personDocumentType(buildMainFormComponent(new PersonDocumentType(), ApplicationFormMicroInfoUtil.PERSON_DOCUMENT_KEY, ApplicationFormMicroInfoUtil.PERSON_DOCUMENT_LABEL, ApplicationFormMicroInfoUtil.PERSON_DOCUMENT_IS_MULTIPLE))
                .selfSufficiency(buildMainFormComponent(new SelfSufficiency(), ApplicationFormMicroInfoUtil.SELF_SUFFICIENCY_KEY, ApplicationFormMicroInfoUtil.SELF_SUFFICIENCY_LABEL, ApplicationFormMicroInfoUtil.SELF_SUFFICIENCY_IS_MULTIPLE))
                .streetType(buildMainFormComponent(new StreetType(), ApplicationFormMicroInfoUtil.STREET_TYPE_KEY, ApplicationFormMicroInfoUtil.STREET_TYPE_LABEL, ApplicationFormMicroInfoUtil.STREET_TYPE_IS_MULTIPLE))
                .vulnerability(buildMainFormComponent(new Vulnerability(), ApplicationFormMicroInfoUtil.VULNERABILITY_KEY, ApplicationFormMicroInfoUtil.VULNERABILITY_LABEL, ApplicationFormMicroInfoUtil.VULNERABILITY_IS_MULTIPLE))
                .statuses(getAllStatuses())
                .build();
    }

    @Override
    public Map<String, String> getAllStatuses() {
        return Arrays.stream(MicroStatus.values())
                .collect(Collectors.toMap(Enum::toString, MicroStatus::getName));
    }

    @Override
    public Map<String, String> getNextStatusesByCurrentStatus(MicroStatus currentStatus, Role role) {
        return switch (role.getName()) {
            case "ROLE_ADMIN" -> getAllStatuses();
            case "ROLE_OPERATOR" -> MicroStatusOperator.getNextStatuses(currentStatus)
                    .stream()
                    .collect(Collectors.toMap(Enum::toString, MicroStatus::getName));
            default -> throw new IllegalArgumentException("Unsupported role: " + role.getName());
        };
    }

    private <T extends ApplicationFormMicroInfoPart> T buildMainFormComponent(T componentObj, String keyStartsWith, String label, String isMultiple) {
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
