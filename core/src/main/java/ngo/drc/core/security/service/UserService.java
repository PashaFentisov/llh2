package ngo.drc.core.security.service;

import ngo.drc.core.security.dto.JwtAuthorizationResponse;
import ngo.drc.core.security.dto.UserAuthorizationDto;

import java.util.UUID;

public interface UserService {
    void saveRegisteredUser(UserAuthorizationDto userDto);

    JwtAuthorizationResponse authorize(UserAuthorizationDto userDto);

    void deleteUserAccount(UUID userId);
}
