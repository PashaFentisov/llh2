package ngo.drc.lapapp.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Victim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private String ipn;
    private String documentSeries;
    private String documentNumber;
    private String documentIssuer;
    private LocalDate documentIssueDate;

    private boolean isVpo;
    private String vpoReferenceNumber;
    private LocalDate vpoReferenceIssueDate;

    private String region;
    private String district;
    private String hromada;
    private String city;
    private String streetType;
    private String streetName;
    private String houseNumber;
    private String pavilionNumber;
    private int apartmentNumber;

    private String regionBeforeMoving;
    private String fullAddressBeforeMoving;

    private String shortBusinessIdeaStory;
    private String businessProductsPlace;
    private String businessProductsManufacturingWay;
    private String businessConsumers;

    private Long grantAmount;

    private String expenseItems;

    private String investedMoneyAmount;

    private boolean employees;
    private int employeesNumber;

    private Long income;

    private int familyMembersNumber;


    private String aboutProgram;

    @ElementCollection
    @CollectionTable(name = "damages", joinColumns = @JoinColumn(name = "damage_id"))
    List<String> conflictDamages = new ArrayList<>();

    private String gender;
    private String grandFunding;

    @ElementCollection
    @CollectionTable(name = "home_leaving_reasons", joinColumns = @JoinColumn(name = "home_leaving_reason_id"))
    List<String> homeLeavingReasons = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "negative_survival_strategies", joinColumns = @JoinColumn(name = "negative_survival_strategy_id"))
    List<String> negativeSurvivalStrategies = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "vulnerabilities", joinColumns = @JoinColumn(name = "vulnerability_id"))
    List<String> vulnerabilities = new ArrayList<>();

    private String personDocumentType;
    private String selfSufficiency;

}