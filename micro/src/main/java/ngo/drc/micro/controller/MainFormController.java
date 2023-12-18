package ngo.drc.micro.controller;

import lombok.AllArgsConstructor;
import ngo.drc.micro.dto.MainFormSavingDto;
import ngo.drc.micro.form.MicroMainForm;
import ngo.drc.micro.service.MainFormInfoService;
import ngo.drc.micro.service.MainFormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/main-form")
public class MainFormController {
    private final MainFormService mainFormService;
    private final MainFormInfoService mainFormInfoService;

    @GetMapping("/info")
    public ResponseEntity<MicroMainForm> getMainFormInfo() {
        return ResponseEntity.ok(mainFormInfoService.getMainFormInfo());
    }

    @PostMapping
    public ResponseEntity<Void> saveMicroMainFormInfo(@RequestBody MainFormSavingDto mainFormSavingDto) {  //todo нічого не повертає, треба?
        mainFormService.saveMicroMainFormInfo(mainFormSavingDto);  //todo валідація на фронтенді чи бекенді
        return ResponseEntity.ok().build();
    }

}
