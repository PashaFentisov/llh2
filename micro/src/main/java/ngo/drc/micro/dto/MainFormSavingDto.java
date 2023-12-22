package ngo.drc.micro.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainFormSavingDto {
    private AddressInfoDto addressInfo;
    private BusinessInfoDto businessInfo;
    private ContactInfoDto contactInfo;
    private DocumentInfoDto documentInfo;
    private boolean isVpo;
    private Long vpoReferenceNumber;
    private OffsetDateTime vpoReferenceIssuedDate;
    private String grandFunding;
    Set<String> conflictDamages = new HashSet<>();
    Set<String> homeLeavingReasons = new HashSet<>();
    private int peopleLeavingWithYou;
    private String threeMonthMovingPlans;
    private String familyAverageMonthlyIncome;
    private String familyAverageMonthlyIncomeBeforeConflict;
    Set<String> vulnerabilities = new HashSet<>();
    private String selfSufficiency;
    Set<String> negativeSurvivalStrategies = new HashSet<>();
    private boolean tookPartInSuchPrograms;
    private String aboutProgram;
}
