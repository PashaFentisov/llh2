package ngo.drc.address.service;


import lombok.RequiredArgsConstructor;
import ngo.drc.address.batch.batchInitV1.district.DistrictData;
import ngo.drc.address.dto.DistrictResponseDto;
import ngo.drc.address.entity.District;
import ngo.drc.address.mapper.DistrictMapper;
import ngo.drc.address.repository.DistrictRepository;
import ngo.drc.locale.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    @Transactional
    public void saveDistrict(DistrictData districtData) {
        District district = districtMapper.toEntity(districtData);
        districtRepository.save(district);
    }

    @Transactional
    public void saveDistricts(List<DistrictData> districtData) {
        List<District> list = districtData.stream().map(districtMapper::toEntity).toList();
        districtRepository.saveAll(list);
    }

    @Transactional(readOnly = true)
    public List<DistrictResponseDto> getDistrictsByRegionCode(String regionCode) {
        return switch (Locale.forLanguageTag(LocaleContextHolder.getLocale()).getLanguage()) {
            case "uk" -> districtRepository.findAllUaByRegionCode(regionCode);
            default -> districtRepository.findAllEnByRegionCode(regionCode);
        };
    }
}
