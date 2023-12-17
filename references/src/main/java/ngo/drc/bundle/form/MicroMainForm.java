package ngo.drc.bundle.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ngo.drc.bundle.domain.*;


@AllArgsConstructor
@Data
@Builder
public class MicroMainForm {
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
}
