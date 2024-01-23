package ngo.drc.micro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ngo.drc.micro.enumeration.MicroDonor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationFormMicroLastVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID applicationFormMicroId;

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

    //new fields

    private boolean lawyerStatus;

    private String statusDescription;

    @Enumerated(EnumType.STRING)
    private MicroDonor donor;

    private String experienceOfSuchActivities;

    private OffsetDateTime dateOfMonitoring;
    private OffsetDateTime dateOfFunding;
    private OffsetDateTime dateOfApproval;

    public void setApplicationFormMicro(ApplicationFormMicro applicationFormMicro) {
        this.applicationFormMicroId = applicationFormMicro.getId();
        this.contactInfo = applicationFormMicro.getContactInfo();
        this.documentInfo = applicationFormMicro.getDocumentInfo();
        this.addressInfo = applicationFormMicro.getAddressInfo();
        this.businessInfo = applicationFormMicro.getBusinessInfo();
        this.isVpo = applicationFormMicro.isVpo();
        this.vpoReferenceNumber = applicationFormMicro.getVpoReferenceNumber();
        this.vpoReferenceIssuedDate = applicationFormMicro.getVpoReferenceIssuedDate();
        this.grandFunding = applicationFormMicro.getGrandFunding();
        this.conflictDamages = applicationFormMicro.getConflictDamages().stream().reduce("", (a, b) -> a + "|" + b);
        this.homeLeavingReasons = applicationFormMicro.getHomeLeavingReasons().stream().reduce("", (a, b) -> a + "|" + b);
        this.peopleLeavingWithYou = applicationFormMicro.getPeopleLeavingWithYou();
        this.threeMonthMovingPlans = applicationFormMicro.getThreeMonthMovingPlans();
        this.familyAverageMonthlyIncome = applicationFormMicro.getFamilyAverageMonthlyIncome();
        this.familyAverageMonthlyIncomeBeforeConflict = applicationFormMicro.getFamilyAverageMonthlyIncomeBeforeConflict();
        this.vulnerabilities = applicationFormMicro.getVulnerabilities().stream().reduce("", (a, b) -> a + "|" + b);
        this.selfSufficiency = applicationFormMicro.getSelfSufficiency();
        this.negativeSurvivalStrategies = applicationFormMicro.getNegativeSurvivalStrategies().stream().reduce("", (a, b) -> a + "|" + b);
        this.tookPartInSuchPrograms = applicationFormMicro.isTookPartInSuchPrograms();
        this.aboutProgram = applicationFormMicro.getAboutProgram();
        this.isDeleted = applicationFormMicro.isDeleted();
        this.lawyerStatus = applicationFormMicro.isLawyerStatus();
        this.statusDescription = applicationFormMicro.getStatusDescription();
        this.donor = applicationFormMicro.getDonor();
        this.experienceOfSuchActivities = applicationFormMicro.getExperienceOfSuchActivities();
        this.dateOfMonitoring = applicationFormMicro.getDateOfMonitoring();
        this.dateOfFunding = applicationFormMicro.getDateOfFunding();
        this.dateOfApproval = applicationFormMicro.getDateOfApproval();
    }
}
