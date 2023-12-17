package ngo.drc.address.service;

import lombok.RequiredArgsConstructor;
import ngo.drc.address.batch.batchInitV1.city.CityData;
import ngo.drc.address.dto.CityResponseDto;
import ngo.drc.address.entity.City;
import ngo.drc.address.mapper.CityMapper;
import ngo.drc.address.repository.CityRepository;
import ngo.drc.bundle.locale.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    @Transactional
    public void saveCity(CityData cityData){
        City city = cityMapper.toEntity(cityData);
        cityRepository.save(city);
    }

    @Transactional(readOnly = true)
    public List<CityResponseDto> getCitiesByHromadaCode(String hromadaCode) {
        return switch (Locale.forLanguageTag(LocaleContextHolder.getLocale()).getLanguage()) {
            case "uk" -> cityRepository.findAllUaByHromadaCode(hromadaCode);
            default -> cityRepository.findAllEnByHromadaCode(hromadaCode);
        };
    }
}
