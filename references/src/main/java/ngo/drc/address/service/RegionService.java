package ngo.drc.address.service;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.batch.batchInitV1.region.RegionData;
import ngo.drc.address.dto.RegionResponseDto;
import ngo.drc.address.entity.Region;
import ngo.drc.address.mapper.RegionMapper;
import ngo.drc.address.repository.RegionRepository;
import ngo.drc.locale.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Transactional
    public void saveRegion(RegionData regionData) {
        Region region = regionMapper.toEntity(regionData);
        regionRepository.save(region);
    }

    @Transactional(readOnly = true)
    public List<RegionResponseDto> getRegions() {
        return switch (Locale.forLanguageTag(LocaleContextHolder.getLocale()).getLanguage()) {
            case "uk" -> regionRepository.findAllUa();
            default -> regionRepository.findAllEn();
        };
    }
}
