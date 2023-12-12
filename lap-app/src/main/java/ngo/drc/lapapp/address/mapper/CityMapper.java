package ngo.drc.lapapp.address.mapper;

import ngo.drc.lapapp.address.batch.batchInitV1.city.CityData;
import ngo.drc.lapapp.address.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper extends GeneralMapper<CityData, City>{
}
