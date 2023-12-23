package ngo.drc.micro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainFormLastVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private DocumentInfo documentInfo;

    @Embedded
    private AddressInfo addressInfo;

    @Embedded
    private BusinessInfo businessInfo;

    private boolean isVpo;
    private Long vpoReferenceNumber;
    private OffsetDateTime vpoReferenceIssuedDate;

    private String grandFunding;

    String conflictDamages;

    String homeLeavingReasons;

    private int peopleLeavingWithYou;
    private String threeMonthMovingPlans;
    private String familyAverageMonthlyIncome;
    private String familyAverageMonthlyIncomeBeforeConflict;

    String vulnerabilities;

    private String selfSufficiency;

    String negativeSurvivalStrategies;

    private boolean tookPartInSuchPrograms;
    private String aboutProgram;
    private boolean isDeleted;

    public MainFormLastVersion(MainForm mainForm) {
        this.userId = mainForm.getId();
        this.contactInfo = mainForm.getContactInfo();
        this.documentInfo = mainForm.getDocumentInfo();
        this.addressInfo = mainForm.getAddressInfo();
        this.businessInfo = mainForm.getBusinessInfo();
        this.isVpo = mainForm.isVpo();
        this.vpoReferenceNumber = mainForm.getVpoReferenceNumber();
        this.vpoReferenceIssuedDate = mainForm.getVpoReferenceIssuedDate();
        this.grandFunding = mainForm.getGrandFunding();
        this.conflictDamages = mainForm.getConflictDamages().stream().reduce("", (a, b) -> a + "|" + b);
        this.homeLeavingReasons = mainForm.getHomeLeavingReasons().stream().reduce("", (a, b) -> a + "|" + b);
        this.peopleLeavingWithYou = mainForm.getPeopleLeavingWithYou();
        this.threeMonthMovingPlans = mainForm.getThreeMonthMovingPlans();
        this.familyAverageMonthlyIncome = mainForm.getFamilyAverageMonthlyIncome();
        this.familyAverageMonthlyIncomeBeforeConflict = mainForm.getFamilyAverageMonthlyIncomeBeforeConflict();
        this.vulnerabilities = mainForm.getVulnerabilities().stream().reduce("", (a, b) -> a + "|" + b);
        this.selfSufficiency = mainForm.getSelfSufficiency();
        this.negativeSurvivalStrategies = mainForm.getNegativeSurvivalStrategies().stream().reduce("", (a, b) -> a + "|" + b);
        this.tookPartInSuchPrograms = mainForm.isTookPartInSuchPrograms();
        this.aboutProgram = mainForm.getAboutProgram();
        this.isDeleted = mainForm.isDeleted();
    }
}
