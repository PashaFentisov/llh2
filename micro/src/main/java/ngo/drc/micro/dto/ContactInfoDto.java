package ngo.drc.micro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInfoDto {
    @NotBlank(message = "Phone number can`t be empty or null")
    private String phoneNumber;
    @NotBlank(message = "Email can`t be empty or null")
    private String email;
    @NotBlank(message = "First name can`t be empty or null")
    private String firstName;
    @NotBlank(message = "Middle name can`t be empty or null")
    private String middleName;
    @NotBlank(message = "Last name can`t be empty or null")
    private String lastName;
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
//    @ValidOffsetDateTime
    private OffsetDateTime dateOfBirth;
    @NotBlank(message = "Gender can`t be empty or null")
    private String gender;
    @NotBlank(message = "IPN can`t be empty or null")
    private String ipn;
}
