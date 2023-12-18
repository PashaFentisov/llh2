package ngo.drc.micro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private String grandFunding; //todo якщо вибрато інше то те що буде введено піде сюди інакше прийде ключ вибраного значення

    @ElementCollection
    @CollectionTable(name = "conflict_damages", joinColumns = @JoinColumn(name = "main_form_id"))
    List<String> conflictDamages = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "home_leaving_reasons", joinColumns = @JoinColumn(name = "main_form_id"))
    List<String> homeLeavingReasons = new ArrayList<>();

    private int peopleLeavingWithYou;
    private String threeMonthMovingPlans;
    private String familyAverageMonthlyIncome;
    private String familyAverageMonthlyIncomeBeforeConflict;

    @ElementCollection
    @CollectionTable(name = "vulnerabilities", joinColumns = @JoinColumn(name = "main_form_id"))
    List<String> vulnerabilities = new ArrayList<>();

    private String selfSufficiency;

    @ElementCollection
    @CollectionTable(name = "negative_survival_strategies", joinColumns = @JoinColumn(name = "main_form_id"))
    List<String> negativeSurvivalStrategies = new ArrayList<>();

    private boolean tookPartInSuchPrograms;
    private String aboutProgram; //todo якщо вибрато інше то те що буде введено піде сюди інакше прийде ключ вибраного значення

    @OneToOne(mappedBy = "mainForm", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Micro micro;

}
