package ngo.drc.address.controller;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.dto.HromadaResponseDto;
import ngo.drc.address.service.HromadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hromadas")
public class HromadaController {
    private final HromadaService hromadaService;

    @GetMapping("/{district-code}")
    public ResponseEntity<List<HromadaResponseDto>> getHromadasByDistrictCode(@PathVariable("district-code") String districtCode) {
        return ResponseEntity.ok(hromadaService.getHromadasByDistrictCode(districtCode));
    }
}
