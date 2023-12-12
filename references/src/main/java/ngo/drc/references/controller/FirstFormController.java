package ngo.drc.references.controller;

import lombok.AllArgsConstructor;
import ngo.drc.references.form.FirstForm;
import ngo.drc.references.service.FirstFormService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/first-form")
public class FirstFormController {
    private final FirstFormService firstFormService;
    @GetMapping
    public FirstForm getFirstFormInfo() {
        return firstFormService.getFirstFormInfo();
    }
}
