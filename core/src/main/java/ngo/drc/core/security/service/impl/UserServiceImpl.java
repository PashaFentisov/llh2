package ngo.drc.core.security.service.impl;

import lombok.RequiredArgsConstructor;
import ngo.drc.core.exception.*;
import ngo.drc.core.security.dto.AuthorizationToken;
import ngo.drc.core.security.dto.JwtAuthorizationResponse;
import ngo.drc.core.security.dto.UserAuthorizationDto;
import ngo.drc.core.security.entity.Role;
import ngo.drc.core.security.entity.User;
import ngo.drc.core.security.mapper.UserAuthorizationMapper;
import ngo.drc.core.security.mapper.UserMapper;
import ngo.drc.core.security.repository.RoleRepository;
import ngo.drc.core.security.repository.UserRepository;
import ngo.drc.core.security.service.JwtService;
import ngo.drc.core.security.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserAuthorizationMapper userAuthorizationMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final String USER_EXISTS_ERROR_MESSAGE = "User with email %s already exists";
    private static final String USER_DOESNT_EXISTS_ERROR_MESSAGE = "User with email %s doesn't exists";
    private static final String USER_WITH_ID_DOESNT_EXISTS_ERROR_MESSAGE = "User with id %s doesn't exists";


    @Transactional
    @Override
    public void saveRegisteredUser(UserAuthorizationDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserExistsException(String.format(USER_EXISTS_ERROR_MESSAGE, userDto.getEmail()));
        }
        User user = userAuthorizationMapper.toEntity(userDto);
        user.setIsVerified(true);  //todo temporary(delete later)
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role roleUser = roleRepository.findRoleByName("ROLE_USER");
        user.setRole(roleUser);
        userRepository.save(user);
    }


    @Transactional
    @Override
    public JwtAuthorizationResponse authorize(UserAuthorizationDto userDto) {
        User user = userRepository.findUserByEmail(userDto.getEmail())
                .orElseThrow(() -> new UserDoesntExistException(
                        String.format(USER_DOESNT_EXISTS_ERROR_MESSAGE, userDto.getEmail())));
        userChecks(userDto, user);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Password is wrong, try again");
        }
        String token = jwtService.generateToken(user);
        OffsetDateTime expiresAt = jwtService.getExpiration(token);
        List<String> permissions = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return new JwtAuthorizationResponse(new AuthorizationToken(token, expiresAt), user.getRole().getName(), permissions);
    }

    private static void userChecks(UserAuthorizationDto userDto, User user) {
        if (!user.isEnabled()) {
            throw new UserIsNotVerifiedException("User email " + userDto.getEmail() + " is not verified");
        }
        if (user.getIsDeleted()) {
            throw new UserDeletedException("User email " + userDto.getEmail() + " was deleted");
        }
    }

    @Transactional
    @Override
    public void deleteUserAccount(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new UsernameNotFoundException(
                    String.format(USER_WITH_ID_DOESNT_EXISTS_ERROR_MESSAGE, userId));
        }
        userRepository.setUserAsDeleted(userId);
    }

}
