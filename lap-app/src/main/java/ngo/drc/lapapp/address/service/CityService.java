package ngo.drc.lapapp.address.service;

import lombok.RequiredArgsConstructor;
import ngo.drc.lapapp.address.batch.batchInitV1.city.CityData;
import ngo.drc.lapapp.address.entity.City;
import ngo.drc.lapapp.address.mapper.CityMapper;
import ngo.drc.lapapp.address.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
