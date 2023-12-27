package ngo.drc.core.security.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthorizationResponse {
    private AuthorizationToken authorizationToken;
    private String role;
    private List<String> permissions;
}
