package ngo.drc.micro.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfo {
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
