package ngo.drc.micro.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
