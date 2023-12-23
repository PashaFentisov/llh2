package ngo.drc.micro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ElementCollection
    @CollectionTable(name = "conflict_damages", joinColumns = @JoinColumn(name = "main_form_id"))
    @BatchSize(size = 1000)
    Set<String> conflictDamages = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "home_leaving_reasons", joinColumns = @JoinColumn(name = "main_form_id"))
    @BatchSize(size = 1000)
    Set<String> homeLeavingReasons = new HashSet<>();

    private int peopleLeavingWithYou;
    private String threeMonthMovingPlans;
    private String familyAverageMonthlyIncome;
    private String familyAverageMonthlyIncomeBeforeConflict;

    @ElementCollection
    @CollectionTable(name = "vulnerabilities", joinColumns = @JoinColumn(name = "main_form_id"))
    @BatchSize(size = 1000)
    Set<String> vulnerabilities = new HashSet<>();

    private String selfSufficiency;

    @ElementCollection
    @BatchSize(size = 1000)
    @CollectionTable(name = "negative_survival_strategies", joinColumns = @JoinColumn(name = "main_form_id"))
    Set<String> negativeSurvivalStrategies = new HashSet<>();

    private boolean tookPartInSuchPrograms;
    private String aboutProgram;
    private boolean isDeleted;
}
