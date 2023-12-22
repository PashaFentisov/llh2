package ngo.drc.micro.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessInfoDto {
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
