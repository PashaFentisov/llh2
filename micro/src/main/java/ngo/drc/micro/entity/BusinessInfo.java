package ngo.drc.micro.entity;


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

    public BusinessInfo(BusinessInfo businessInfo) {
        this.shortBusinessIdeaDescription = businessInfo.getShortBusinessIdeaDescription();
        this.businessManufacturingPlace = businessInfo.getBusinessManufacturingPlace();
        this.businessManufacturingWay = businessInfo.getBusinessManufacturingWay();
        this.businessConsumers = businessInfo.getBusinessConsumers();
        this.neededGrantAmount = businessInfo.getNeededGrantAmount();
        this.grantExpenses = businessInfo.getGrantExpenses();
        this.investedMoneyAmount = businessInfo.getInvestedMoneyAmount();
        this.employeesHave = businessInfo.isEmployeesHave();
        this.numberOfEmployees = businessInfo.getNumberOfEmployees();
        this.businessMonthlyIncome = businessInfo.getBusinessMonthlyIncome();
    }
}
