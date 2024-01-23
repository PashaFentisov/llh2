package ngo.drc.statistic.controller;

import lombok.RequiredArgsConstructor;
import ngo.drc.statistic.entity.Target;
import ngo.drc.statistic.service.TargetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/target")
@RequiredArgsConstructor
public class TargetRestController {

    private final TargetService targetService;

    @GetMapping
    public Target getTarget() {
        return targetService.calculateTarget();
    }
}
