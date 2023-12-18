package ngo.drc.micro.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    private String phoneNumber;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private OffsetDateTime dateOfBirth;
    private String gender;
    private String ipn;
}
