package ngo.drc.micro.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressInfoUpdateDto {
    private String region;
    private String district;
    private String hromada;
    private String city;
    private String streetType;
    private String streetName;
    private String buildingNumber;
    @Min(value = 0, message = "Apartment number can`t be negative")
    private Long apartmentNumber;
    private String pavilionNumber;
    private String regionBeforeMoving;
    private String fullAddressBeforeMoving;
}
