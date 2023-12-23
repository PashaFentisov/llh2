package ngo.drc.micro.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Email;
import lombok.*;
import ngo.drc.core.util.CustomOffsetDateTimeDeserializer;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInfoUpdateDto {
    private String phoneNumber;
    @Email(message = "Email should be valid")
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
    private OffsetDateTime dateOfBirth;
    private String gender;
    private String ipn;
}
