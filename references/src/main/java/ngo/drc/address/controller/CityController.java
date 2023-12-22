package ngo.drc.address.controller;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.dto.CityResponseDto;
import ngo.drc.address.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/references/cities")
public class CityController {
    private final CityService cityService;

    @GetMapping("/{hromada-code}")
    public ResponseEntity<List<CityResponseDto>> getCitiesByHromadaCode(@PathVariable("hromada-code") String hromadaCode) {
        return ResponseEntity.ok(cityService.getCitiesByHromadaCode(hromadaCode));
    }
}
