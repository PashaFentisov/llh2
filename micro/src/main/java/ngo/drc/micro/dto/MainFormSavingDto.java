package ngo.drc.micro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ngo.drc.core.annotation.ValidOffsetDateTime;
import ngo.drc.core.util.CustomOffsetDateTimeDeserializer;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainFormSavingDto {
    @NotNull(message = "Address info number can`t be null")
    @Valid
    private AddressInfoDto addressInfo;
    @NotNull(message = "Business info number can`t be null")
    @Valid
    private BusinessInfoDto businessInfo;
    @NotNull(message = "Contact info number can`t be null")
    @Valid
    private ContactInfoDto contactInfo;
    @NotNull(message = "Document info number can`t be null")
    @Valid
    private DocumentInfoDto documentInfo;
    @NotNull(message = "Is vpo can`t be null")
    private boolean isVpo;
    @NotNull(message = "VPO reference number can`t be null")
    private Long vpoReferenceNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
    @ValidOffsetDateTime
    private OffsetDateTime vpoReferenceIssuedDate;
    @NotBlank(message = "Grand funding can`t be empty or null")
    private String grandFunding;
    @NotNull(message = "Conflict damages can`t be null")
    Set<String> conflictDamages = new HashSet<>();
    @NotNull(message = "Home leaving reasons can`t be null")
    Set<String> homeLeavingReasons = new HashSet<>();
    @NotNull(message = "People leaving with you can`t be null")
    @Min(value = 0, message = "People leaving with you can`t be negative")
    private int peopleLeavingWithYou;
    @NotBlank(message = "Three month moving plans  can`t be empty or null")
    private String threeMonthMovingPlans;
    @NotBlank(message = "Family average monthly income can`t be empty or null")
    private String familyAverageMonthlyIncome;
    @NotBlank(message = "Family average monthly income before conflict can`t be empty or null")
    private String familyAverageMonthlyIncomeBeforeConflict;
    @NotNull(message = "Vulnerabilities can`t be null")
    Set<String> vulnerabilities = new HashSet<>();
    @NotBlank(message = "Self sufficiency can`t be empty or null")
    private String selfSufficiency;
    @NotNull(message = "Negative survival strategies can`t be null")
    Set<String> negativeSurvivalStrategies = new HashSet<>();
    @NotNull(message = "Took part in such programs can`t be null")
    private boolean tookPartInSuchPrograms;
    @NotBlank(message = "About program can`t be empty or null")
    private String aboutProgram;
}
