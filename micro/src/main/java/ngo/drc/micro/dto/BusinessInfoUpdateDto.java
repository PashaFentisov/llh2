package ngo.drc.micro.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessInfoUpdateDto {
    private String shortBusinessIdeaDescription;
    private String businessManufacturingPlace;
    private String businessManufacturingWay;
    private String businessConsumers;
    private String neededGrantAmount;
    private String grantExpenses;
    private String investedMoneyAmount;
    private Boolean employeesHave;
    @Min(value = 0, message = "Number of employees can`t be negative")
    private Long numberOfEmployees;
    private Long businessMonthlyIncome;
}
