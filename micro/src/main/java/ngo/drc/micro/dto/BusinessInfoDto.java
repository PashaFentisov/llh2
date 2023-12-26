package ngo.drc.micro.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessInfoDto {
    @NotBlank(message = "Short business idea description can`t be empty or null")
    private String shortBusinessIdeaDescription;
    @NotBlank(message = "Business manufacturing place can`t be empty or null")
    private String businessManufacturingPlace;
    @NotBlank(message = "Business manufacturing way can`t be empty or null")
    private String businessManufacturingWay;
    @NotBlank(message = "Business consumers can`t be empty or null")
    private String businessConsumers;
    @NotBlank(message = "Needed grant a mount can`t be empty or null")
    private String neededGrantAmount;
    @NotBlank(message = "Grand expenses can`t be empty or null")
    private String grantExpenses;
    @NotBlank(message = "Invested money amount can`t be empty or null")
    private String investedMoneyAmount;
    @NotNull(message = "Employees have can`t be null")
    private boolean employeesHave;
    @NotNull(message = "Number of employees can`t be null")
    @Min(value = 0, message = "Number of employees can`t be negative")
    private Long numberOfEmployees;
    @NotNull(message = "Business monthly income can`t be null")
    private Long businessMonthlyIncome;
}
