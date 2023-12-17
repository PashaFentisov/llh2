package ngo.drc.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistrictResponseDto {
    private Long id;
    private String code;
    private String regionCode;
    private String name;
}
