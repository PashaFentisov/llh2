package ngo.drc.address.controller;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.dto.DistrictResponseDto;
import ngo.drc.address.service.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/districts")
public class DistrictController {
    private final DistrictService districtService;

    @GetMapping("/{region-code}")
    public ResponseEntity<List<DistrictResponseDto>> getDistrictsByRegionCode(@PathVariable("region-code") String regionCode) {
        return ResponseEntity.ok(districtService.getDistrictsByRegionCode(regionCode));
    }
}
