package ngo.drc.micro.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ngo.drc.exception.EntityValidationException;
import ngo.drc.micro.dto.MainFormResponseDto;
import ngo.drc.micro.dto.MainFormSavingDto;
import ngo.drc.micro.form.MicroMainForm;
import ngo.drc.micro.service.MainFormInfoService;
import ngo.drc.micro.service.MainFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @GetMapping("/info")
    public ResponseEntity<MicroMainForm> getMainFormInfo() {
        return ResponseEntity.ok(mainFormInfoService.getMainFormInfo());
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
}
