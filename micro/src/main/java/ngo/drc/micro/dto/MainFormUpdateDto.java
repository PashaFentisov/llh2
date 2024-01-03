package ngo.drc.micro.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.*;
import ngo.drc.core.util.CustomOffsetDateTimeDeserializer;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainFormUpdateDto {
    @Valid
    private AddressInfoUpdateDto addressInfo;
    @Valid
    private BusinessInfoUpdateDto businessInfo;
    @Valid
    private ContactInfoUpdateDto contactInfo;
    @Valid
    private DocumentInfoUpdateDto documentInfo;
    private String status;
    private Boolean isVpo;
    private Long vpoReferenceNumber;
    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
    private OffsetDateTime vpoReferenceIssuedDate;
    private String grandFunding;
    Set<String> conflictDamages;
    Set<String> homeLeavingReasons;
    @Min(value = 0, message = "People leaving with you can`t be negative")
    private Integer peopleLeavingWithYou;
    private String threeMonthMovingPlans;
    private String familyAverageMonthlyIncome;
    private String familyAverageMonthlyIncomeBeforeConflict;
    Set<String> vulnerabilities;
    private String selfSufficiency;
    Set<String> negativeSurvivalStrategies;
    private Boolean tookPartInSuchPrograms;
    private String aboutProgram;
}

