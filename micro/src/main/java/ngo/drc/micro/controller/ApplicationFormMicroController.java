package ngo.drc.micro.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ngo.drc.core.dto.GenericFormResponse;
import ngo.drc.core.endpoint.PageResponse;
import ngo.drc.core.exception.BigSizeException;
import ngo.drc.core.exception.EntityValidationException;
import ngo.drc.micro.dto.ApplicationFormMicroResponseDto;
import ngo.drc.micro.dto.ApplicationFormMicroSavingDto;
import ngo.drc.micro.dto.ApplicationFormMicroUpdateDto;
import ngo.drc.micro.dto.LawyerStatusRequest;
import ngo.drc.micro.form.ApplicationFormMicroInfo;
import ngo.drc.micro.service.ApplicationFormMicroService;
import ngo.drc.micro.service.impl.ApplicationFormMicroInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/micro/application-form")
public class ApplicationFormMicroController {
    private final ApplicationFormMicroService applicationFormMicroService;
    private final ApplicationFormMicroInfoServiceImpl mainFormInfoService;
    private final Logger logger = LoggerFactory.getLogger(ApplicationFormMicroController.class);

    @GetMapping
    public ResponseEntity<GenericFormResponse<ApplicationFormMicroInfo,
            PageResponse<ApplicationFormMicroResponseDto>>> getApplicationMicroForms
            (@RequestParam(required = false, defaultValue = "0") int page,
             @RequestParam(required = false, defaultValue = "10") int size,
             @RequestParam(required = false, defaultValue = "id") String sort) { //todo filter

        if (size > 100) {
            throw new BigSizeException("You can get maximum 100 actors at one time");
        }
        GenericFormResponse<ApplicationFormMicroInfo, PageResponse<ApplicationFormMicroResponseDto>> allApplicationMicroForms = applicationFormMicroService.
                getAllApplicationFormsMicro(PageRequest.of(page, size, Sort.by(sort)));
        return ResponseEntity.ok(allApplicationMicroForms);
    }

    @GetMapping("/info")
    public ResponseEntity<ApplicationFormMicroInfo> getApplicationFormMicroInfo() {
        return ResponseEntity.ok(mainFormInfoService.getApplicationFormMicroInfo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericFormResponse<ApplicationFormMicroInfo, ApplicationFormMicroResponseDto>> getMainForm
            (@PathVariable UUID id, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(applicationFormMicroService.getApplicationFormMicro(id, userDetails.getUsername()));
    }

    @PostMapping
    public ResponseEntity<ApplicationFormMicroResponseDto> saveMicroMainForm(@RequestBody @Valid ApplicationFormMicroSavingDto applicationFormMicroSavingDto,
                                                                             Errors errors) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(er -> logger.error(er.getDefaultMessage()));
            throw new EntityValidationException("Validation failed", errors);
        }
        ApplicationFormMicroResponseDto applicationFormMicroResponseDto = applicationFormMicroService.saveApplicationFormMicro(applicationFormMicroSavingDto);
        return ResponseEntity.ok(applicationFormMicroResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMainForm(@PathVariable UUID id) {
        applicationFormMicroService.deleteApplicationFormMicro(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/set-as-not-deleted/{id}")
    public ResponseEntity<Object> setAsNotDeletedApplicationFormMicro(@PathVariable UUID id) {
        applicationFormMicroService.setAsNotDeletedApplicationFormMicro(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/set-lawyer-status/{id}")
    public ResponseEntity<ApplicationFormMicroResponseDto> setLawyerStatusForApplicationFormMicro(@PathVariable UUID id,
                                                                                                  @RequestBody LawyerStatusRequest lawyerStatusRequest) {
        ApplicationFormMicroResponseDto response = applicationFormMicroService
                .setLawyerStatusForApplicationFormMicro(id, lawyerStatusRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/revert-to-last-version/{id}")
    public ResponseEntity<ApplicationFormMicroResponseDto> revertApplicationFormMicroToLastVersion(@PathVariable UUID id) {
        ApplicationFormMicroResponseDto applicationFormMicroResponseDto = applicationFormMicroService.revertApplicationFormMicroToLastVersion(id);
        return ResponseEntity.ok(applicationFormMicroResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApplicationFormMicroResponseDto> updateApplicationFormMicro(@PathVariable UUID id,
                                                                               @RequestBody @Valid ApplicationFormMicroUpdateDto applicationFormMicroUpdateDto,
                                                                               Errors errors,
                                                                               @AuthenticationPrincipal UserDetails userDetails) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(er -> logger.error(er.getDefaultMessage()));
            throw new EntityValidationException("Validation failed", errors);
        }
        ApplicationFormMicroResponseDto applicationFormMicroResponseDto = applicationFormMicroService
                .updateApplicationFormMicro(applicationFormMicroUpdateDto, id, userDetails.getUsername());
        return ResponseEntity.ok(applicationFormMicroResponseDto);
    }
}
