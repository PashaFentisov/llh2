package ngo.drc.micro.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ngo.drc.micro.dto.*;

import java.util.Map;


@AllArgsConstructor
@Data
@Builder
public class ApplicationFormMicroInfo {
    private AboutProgram aboutProgram;
    private ConflictDamage conflictDamage;
    private Gender gender;
    private GrandFunding grandFunding;
    private NegativeSurvivalStrategy negativeSurvivalStrategy;
    private HomeLeavingReason homeLeavingReason;
    private PersonDocumentType personDocumentType;
    private SelfSufficiency selfSufficiency;
    private StreetType streetType;
    private Vulnerability vulnerability;
    private Map<String, String> statuses;
}
