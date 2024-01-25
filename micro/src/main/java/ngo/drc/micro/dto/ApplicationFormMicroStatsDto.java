package ngo.drc.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ngo.drc.micro.enumeration.MicroDonor;
import ngo.drc.micro.enumeration.MicroStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApplicationFormMicroStatsDto {
    private MicroDonor donor;
    private MicroStatus status;
}
