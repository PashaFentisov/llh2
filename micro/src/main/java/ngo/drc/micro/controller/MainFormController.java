package ngo.drc.micro.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ngo.drc.core.dto.GenericFormResponse;
import ngo.drc.core.endpoint.PageResponse;
import ngo.drc.core.exception.BigSizeException;
import ngo.drc.core.exception.EntityValidationException;
import ngo.drc.micro.dto.MainFormResponseDto;
import ngo.drc.micro.dto.MainFormSavingDto;
import ngo.drc.micro.dto.MainFormUpdateDto;
import ngo.drc.micro.form.MainFormInfo;
import ngo.drc.micro.service.MainFormService;
import ngo.drc.micro.service.impl.MainFormInfoServiceImpl;
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
@RequestMapping("api/v1/micro/main-form")
public class MainFormController {
    private final MainFormService mainFormService;
    private final MainFormInfoServiceImpl mainFormInfoService;
    private final Logger logger = LoggerFactory.getLogger(MainFormController.class);

    @GetMapping
    public ResponseEntity<GenericFormResponse<MainFormInfo,
            PageResponse<MainFormResponseDto>>> getMainForms
            (@RequestParam(required = false, defaultValue = "0") int page,
             @RequestParam(required = false, defaultValue = "10") int size,
             @RequestParam(required = false, defaultValue = "id") String sort) { //todo filter

        if (size > 100) {
            throw new BigSizeException("You can get maximum 100 actors at one time");
        }
        GenericFormResponse<MainFormInfo, PageResponse<MainFormResponseDto>> allMainForms = mainFormService.
                getAllMainForms(PageRequest.of(page, size, Sort.by(sort)));
        return ResponseEntity.ok(allMainForms);
    }

    @GetMapping("/info")
    public ResponseEntity<MainFormInfo> getMainFormInfo() {
        return ResponseEntity.ok(mainFormInfoService.getMainFormInfo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericFormResponse<MainFormInfo, MainFormResponseDto>> getMainForm
            (@PathVariable UUID id, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(mainFormService.getMainForm(id, userDetails.getUsername())); //todo return only next statuses for operators and all for admin
    }

    @PostMapping
    public ResponseEntity<MainFormResponseDto> saveMicroMainForm(@RequestBody @Valid MainFormSavingDto mainFormSavingDto,
                                                                 Errors errors) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(er -> logger.error(er.getDefaultMessage()));
            throw new EntityValidationException("Validation failed", errors);
        }
        MainFormResponseDto mainFormResponseDto = mainFormService.saveMicroMainForm(mainFormSavingDto);
        return ResponseEntity.ok(mainFormResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMainForm(@PathVariable UUID id) {
        mainFormService.deleteMicroMainForm(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/set-as-not-deleted/{id}")
    public ResponseEntity<Object> setAsNotDeletedMainForm(@PathVariable UUID id) {
        mainFormService.setAsNotDeletedMainForm(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/revert-to-last-version/{id}")
    public ResponseEntity<MainFormResponseDto> revertMainFormToLastVersion(@PathVariable UUID id) {
        MainFormResponseDto mainFormResponseDto = mainFormService.revertMainFormToLastVersion(id);
        return ResponseEntity.ok(mainFormResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MainFormResponseDto> updateMicroMainForm(@PathVariable UUID id,
                                                                   @RequestBody @Valid MainFormUpdateDto mainFormUpdateDto,
                                                                   Errors errors) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(er -> logger.error(er.getDefaultMessage()));
            throw new EntityValidationException("Validation failed", errors);
        }
        MainFormResponseDto mainFormResponseDto = mainFormService.updateMicroMainForm(mainFormUpdateDto, id);
        return ResponseEntity.ok(mainFormResponseDto);
    }
}
