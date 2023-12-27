package ngo.drc.core.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ngo.drc.core.exception.EntityValidationException;
import ngo.drc.core.security.dto.JwtAuthorizationResponse;
import ngo.drc.core.security.dto.UserAuthorizationDto;
import ngo.drc.core.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/core/users")
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String VALIDATION_EXCEPTION_MESSAGE = "Validation failed";

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserAuthorizationDto userDto, Errors errors) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(er -> logger.error(er.getDefaultMessage()));
            throw new EntityValidationException(VALIDATION_EXCEPTION_MESSAGE, errors);
        }
        userService.saveRegisteredUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authorize")
    public ResponseEntity<JwtAuthorizationResponse> authorize(@RequestBody @Valid UserAuthorizationDto userDto, Errors errors) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(er -> logger.error(er.getDefaultMessage()));
            throw new EntityValidationException(VALIDATION_EXCEPTION_MESSAGE, errors);
        }
        return ResponseEntity.ok(userService.authorize(userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable("userId") UUID userId) {
        userService.deleteUserAccount(userId);
        return ResponseEntity.noContent().build();
    }
}
