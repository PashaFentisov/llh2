package ngo.drc.core.security.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private Boolean isDeleted;
    private String username;
    private String phoneNumber;
    private String photoUrl;
}
