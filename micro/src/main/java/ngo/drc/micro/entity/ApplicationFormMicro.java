package ngo.drc.micro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ngo.drc.micro.enumeration.MicroDonor;
import ngo.drc.micro.enumeration.MicroStatus;
import org.hibernate.annotations.BatchSize;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationFormMicro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private DocumentInfo documentInfo;

    @Embedded
    private AddressInfo addressInfo;

    @Embedded
    private BusinessInfo businessInfo;

    @Enumerated(EnumType.STRING)
    private MicroStatus status;

    private boolean isVpo;
    private Long vpoReferenceNumber;
    private OffsetDateTime vpoReferenceIssuedDate;

    private String grandFunding;

    @ElementCollection
    @CollectionTable(name = "conflict_damages", joinColumns = @JoinColumn(name = "application_form_micro_id"))
    @BatchSize(size = 1000)
    Set<String> conflictDamages = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "home_leaving_reasons", joinColumns = @JoinColumn(name = "application_form_micro_id"))
    @BatchSize(size = 1000)
    Set<String> homeLeavingReasons = new HashSet<>();

    private int peopleLeavingWithYou;
    private String threeMonthMovingPlans;
    private String familyAverageMonthlyIncome;
    private String familyAverageMonthlyIncomeBeforeConflict;

    @ElementCollection
    @CollectionTable(name = "vulnerabilities", joinColumns = @JoinColumn(name = "application_form_micro_id"))
    @BatchSize(size = 1000)
    Set<String> vulnerabilities = new HashSet<>();

    private String selfSufficiency;

    @ElementCollection
    @BatchSize(size = 1000)
    @CollectionTable(name = "negative_survival_strategies", joinColumns = @JoinColumn(name = "application_form_micro_id"))
    Set<String> negativeSurvivalStrategies = new HashSet<>();

    private boolean tookPartInSuchPrograms;
    private String aboutProgram;
    private boolean isDeleted;


    //missing(new) fields

    private boolean lawyerStatus;

    private String statusDescription;

    @Enumerated(EnumType.STRING)
    private MicroDonor donor;

    private String experienceOfSuchActivities;

    private OffsetDateTime dateOfMonitoring;
    private OffsetDateTime dateOfFunding;
    private OffsetDateTime dateOfApproval;
    private int age;

    private OffsetDateTime dateOfCreation;
    private OffsetDateTime lastUpdate;




    public ApplicationFormMicro(ApplicationFormMicro applicationFormMicro) {
        this.id = applicationFormMicro.getId();
        this.contactInfo = new ContactInfo(applicationFormMicro.getContactInfo());
        this.documentInfo = new DocumentInfo(applicationFormMicro.getDocumentInfo());
        this.addressInfo = new AddressInfo(applicationFormMicro.getAddressInfo());
        this.businessInfo = new BusinessInfo(applicationFormMicro.getBusinessInfo());
        this.isVpo = applicationFormMicro.isVpo();
        this.vpoReferenceNumber = applicationFormMicro.getVpoReferenceNumber();
        this.vpoReferenceIssuedDate = applicationFormMicro.getVpoReferenceIssuedDate();
        this.grandFunding = applicationFormMicro.getGrandFunding();
        this.conflictDamages = new HashSet<>(applicationFormMicro.getConflictDamages());
        this.homeLeavingReasons = new HashSet<>(applicationFormMicro.getHomeLeavingReasons());
        this.peopleLeavingWithYou = applicationFormMicro.getPeopleLeavingWithYou();
        this.threeMonthMovingPlans = applicationFormMicro.getThreeMonthMovingPlans();
        this.familyAverageMonthlyIncome = applicationFormMicro.getFamilyAverageMonthlyIncome();
        this.familyAverageMonthlyIncomeBeforeConflict = applicationFormMicro.getFamilyAverageMonthlyIncomeBeforeConflict();
        this.vulnerabilities = new HashSet<>(applicationFormMicro.getVulnerabilities());
        this.selfSufficiency = applicationFormMicro.getSelfSufficiency();
        this.negativeSurvivalStrategies = new HashSet<>(applicationFormMicro.getNegativeSurvivalStrategies());
        this.tookPartInSuchPrograms = applicationFormMicro.isTookPartInSuchPrograms();
        this.aboutProgram = applicationFormMicro.getAboutProgram();
        this.isDeleted = applicationFormMicro.isDeleted();
        this.status = applicationFormMicro.getStatus();
        this.lawyerStatus = applicationFormMicro.isLawyerStatus();
        this.statusDescription = applicationFormMicro.getStatusDescription();
        this.donor = applicationFormMicro.getDonor();
        this.experienceOfSuchActivities = applicationFormMicro.getExperienceOfSuchActivities();
        this.dateOfMonitoring = applicationFormMicro.getDateOfMonitoring();
        this.dateOfFunding = applicationFormMicro.getDateOfFunding();
        this.dateOfApproval = applicationFormMicro.getDateOfApproval();
        this.age = applicationFormMicro.getAge();
        this.dateOfCreation = applicationFormMicro.getDateOfCreation();
    }

    public void revertToLastVersion(ApplicationFormMicroLastVersion applicationFormMicroLastVersion) {
        this.contactInfo = applicationFormMicroLastVersion.getContactInfo();
        this.documentInfo = applicationFormMicroLastVersion.getDocumentInfo();
        this.addressInfo = applicationFormMicroLastVersion.getAddressInfo();
        this.businessInfo = applicationFormMicroLastVersion.getBusinessInfo();
        this.isVpo = applicationFormMicroLastVersion.isVpo();
        this.vpoReferenceNumber = applicationFormMicroLastVersion.getVpoReferenceNumber();
        this.vpoReferenceIssuedDate = applicationFormMicroLastVersion.getVpoReferenceIssuedDate();
        this.grandFunding = applicationFormMicroLastVersion.getGrandFunding();
        this.conflictDamages = Arrays.stream(applicationFormMicroLastVersion.getConflictDamages().split("\\|"))
                .filter(conflictDamage -> !conflictDamage.isEmpty())
                .collect(Collectors.toSet());
        this.homeLeavingReasons = Arrays.stream(applicationFormMicroLastVersion.getHomeLeavingReasons().split("\\|"))
                .filter(homeLeavingReason -> !homeLeavingReason.isEmpty())
                .collect(Collectors.toSet());
        this.peopleLeavingWithYou = applicationFormMicroLastVersion.getPeopleLeavingWithYou();
        this.threeMonthMovingPlans = applicationFormMicroLastVersion.getThreeMonthMovingPlans();
        this.familyAverageMonthlyIncome = applicationFormMicroLastVersion.getFamilyAverageMonthlyIncome();
        this.familyAverageMonthlyIncomeBeforeConflict = applicationFormMicroLastVersion.getFamilyAverageMonthlyIncomeBeforeConflict();
        this.vulnerabilities = Arrays.stream(applicationFormMicroLastVersion.getVulnerabilities().split("\\|"))
                .filter(vulnerability -> !vulnerability.isEmpty())
                .collect(Collectors.toSet());
        this.selfSufficiency = applicationFormMicroLastVersion.getSelfSufficiency();
        this.negativeSurvivalStrategies = Arrays.stream(applicationFormMicroLastVersion.getNegativeSurvivalStrategies().split("\\|"))
                .filter(negativeSurvivalStrategy -> !negativeSurvivalStrategy.isEmpty())
                .collect(Collectors.toSet());
        this.tookPartInSuchPrograms = applicationFormMicroLastVersion.isTookPartInSuchPrograms();
        this.aboutProgram = applicationFormMicroLastVersion.getAboutProgram();
        this.isDeleted = applicationFormMicroLastVersion.isDeleted();
        this.lawyerStatus = applicationFormMicroLastVersion.isLawyerStatus();
        this.statusDescription = applicationFormMicroLastVersion.getStatusDescription();
        this.donor = applicationFormMicroLastVersion.getDonor();
        this.experienceOfSuchActivities = applicationFormMicroLastVersion.getExperienceOfSuchActivities();

        this.dateOfMonitoring = applicationFormMicroLastVersion.getDateOfMonitoring();
        this.dateOfFunding = applicationFormMicroLastVersion.getDateOfFunding();
        this.dateOfApproval = applicationFormMicroLastVersion.getDateOfApproval();
    }

}
