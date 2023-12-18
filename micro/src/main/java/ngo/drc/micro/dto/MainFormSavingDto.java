package ngo.drc.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainFormSavingDto {
    private AddressInfoDto addressInfo;
    private BusinessInfoDto businessInfo;
    private ContactInfoDto contactInfo;
    private DocumentInfoDto documentInfo;
    private boolean isVpo;
    private Long vpoReferenceNumber;
    private OffsetDateTime vpoReferenceIssuedDate;
    private String grandFunding;
    List<String> conflictDamages = new ArrayList<>();
    List<String> homeLeavingReasons = new ArrayList<>();
    private int peopleLeavingWithYou;
    private String threeMonthMovingPlans;
    private String familyAverageMonthlyIncome;
    private String familyAverageMonthlyIncomeBeforeConflict;
    List<String> vulnerabilities = new ArrayList<>();
    private String selfSufficiency;
    List<String> negativeSurvivalStrategy = new ArrayList<>();
    private boolean tookPartInSuchPrograms;
    private String aboutProgram;
}
