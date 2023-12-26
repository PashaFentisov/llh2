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

    public ContactInfo(ContactInfo contactInfo) {
        this.phoneNumber = contactInfo.getPhoneNumber();
        this.email = contactInfo.getEmail();
        this.firstName = contactInfo.getFirstName();
        this.middleName = contactInfo.getMiddleName();
        this.lastName = contactInfo.getLastName();
        this.dateOfBirth = contactInfo.getDateOfBirth();
        this.gender = contactInfo.getGender();
        this.ipn = contactInfo.getIpn();
    }
}
