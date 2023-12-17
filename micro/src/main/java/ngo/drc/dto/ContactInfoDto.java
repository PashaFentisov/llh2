package ngo.drc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoDto {
    private String phoneNumber;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private OffsetDateTime dateOfBirth;
    private String gender;
    private String ipn;
}
