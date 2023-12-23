package ngo.drc.micro.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ngo.drc.core.dto.GenericFormResponse;
import ngo.drc.core.dto.GenericPageFormResponse;
import ngo.drc.endpoint.PageResponse;
import ngo.drc.exception.BigSizeException;
import ngo.drc.exception.EntityValidationException;
import ngo.drc.micro.dto.MainFormResponseDto;
import ngo.drc.micro.dto.MainFormSavingDto;
import ngo.drc.micro.dto.MainFormUpdateDto;
import ngo.drc.micro.form.MainFormInfo;
import ngo.drc.micro.service.MainFormInfoService;
import ngo.drc.micro.service.MainFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/micro/main-form")
public class MainFormController {
    private final MainFormService mainFormService;
    private final MainFormInfoService mainFormInfoService;
    private final Logger logger = LoggerFactory.getLogger(MainFormController.class);

    @GetMapping
    public ResponseEntity<GenericPageFormResponse<MainFormInfo,
            PageResponse<MainFormResponseDto>>> getMainForms
            (@RequestParam(required = false, defaultValue = "0") int page,
             @RequestParam(required = false, defaultValue = "10") int size,
             @RequestParam(required = false, defaultValue = "id") String sort) { //todo filter

        if (size > 100) {
            throw new BigSizeException("You can get maximum 100 actors at one time");
        }
        GenericPageFormResponse<MainFormInfo, PageResponse<MainFormResponseDto>> allMainForms = mainFormService.
                getAllMainForms(PageRequest.of(page, size, Sort.by(sort)));
        return ResponseEntity.ok(allMainForms);
    }

    @GetMapping("/info")
    public ResponseEntity<MainFormInfo> getMainFormInfo() {
        return ResponseEntity.ok(mainFormInfoService.getMainFormInfo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericFormResponse<MainFormInfo, MainFormResponseDto>> getMainForm(@PathVariable Long id) {
        return ResponseEntity.ok(mainFormService.getMainForm(id));
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
    public ResponseEntity<Object> deleteMicroMainForm(@PathVariable Long id) {
        mainFormService.deleteMicroMainForm(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MainFormResponseDto> updateMicroMainForm(@PathVariable Long id,
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
