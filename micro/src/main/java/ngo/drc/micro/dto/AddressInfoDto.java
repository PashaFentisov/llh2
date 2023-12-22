package ngo.drc.micro.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
