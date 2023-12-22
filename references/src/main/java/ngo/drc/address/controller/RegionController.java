package ngo.drc.address.controller;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.dto.RegionResponseDto;
import ngo.drc.address.service.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/references/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionResponseDto>> getRegions() {
        return ResponseEntity.ok(regionService.getRegions());
    }
}
