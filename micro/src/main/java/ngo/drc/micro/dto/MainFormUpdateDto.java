package ngo.drc.micro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
public class MainFormUpdateDto {
    @Valid
    private AddressInfoUpdateDto addressInfo;
    @Valid
    private BusinessInfoUpdateDto businessInfo;
    @Valid
    private ContactInfoUpdateDto contactInfo;
    @Valid
    private DocumentInfoUpdateDto documentInfo;
    private boolean isVpo;
    private Long vpoReferenceNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
    @ValidOffsetDateTime
    private OffsetDateTime vpoReferenceIssuedDate;
    private String grandFunding;
    Set<String> conflictDamages = new HashSet<>();
    Set<String> homeLeavingReasons = new HashSet<>();
    @Min(value = 0, message = "People leaving with you can`t be negative")
    private int peopleLeavingWithYou;
    private String threeMonthMovingPlans;
    private String familyAverageMonthlyIncome;
    private String familyAverageMonthlyIncomeBeforeConflict;
    Set<String> vulnerabilities = new HashSet<>();
    private String selfSufficiency;
    Set<String> negativeSurvivalStrategies = new HashSet<>();
    private boolean tookPartInSuchPrograms;
    private String aboutProgram;
}
