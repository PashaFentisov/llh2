package ngo.drc.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessInfo {
    private String shortBusinessIdeaDescription;
    private String businessManufacturingPlace;
    private String businessManufacturingWay;
    private String businessConsumers;
    private String neededGrantAmount;
    private String grantExpenses;
    private String investedMoneyAmount;
    private boolean employeesHave;
    private Long numberOfEmployees;
    private Long businessMonthlyIncome;
}
