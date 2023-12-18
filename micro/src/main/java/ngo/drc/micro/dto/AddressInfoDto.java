package ngo.drc.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfoDto {
    private String region;
    private String district;
    private String hromada;
    private String city;
    private String streetType;
    private String streetName;
    private String buildingNumber;
    private Long apartmentNumber;
    private String pavilionNumber;
    private String regionBeforeMoving;
    private String fullAddressBeforeMoving;
}
