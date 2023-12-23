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
public class AddressInfoDto {
    @NotBlank(message = "Region can`t be empty or null")
    private String region;
    @NotBlank(message = "District can`t be empty or null")
    private String district;
    @NotBlank(message = "Hromada can`t be empty or null")
    private String hromada;
    @NotBlank(message = "City can`t be empty or null")
    private String city;
    @NotBlank(message = "Street type can`t be empty or null")
    private String streetType;
    @NotBlank(message = "Street name can`t be empty or null")
    private String streetName;
    @NotBlank(message = "Building number can`t be empty or null")
    private String buildingNumber;
    @NotNull(message = "Apartment number can`t be null")
    @Min(value = 0, message = "Apartment number can`t be negative")
    private Long apartmentNumber;
    @NotBlank(message = "Pavilion number can`t be empty or null")
    private String pavilionNumber;
    @NotBlank(message = "Region before moving can`t be empty or null")
    private String regionBeforeMoving;
    @NotBlank(message = "Full address before moving can`t be empty or null")
    private String fullAddressBeforeMoving;
}
