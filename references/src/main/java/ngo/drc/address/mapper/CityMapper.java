package ngo.drc.address.mapper;


import ngo.drc.address.batch.batchInitV1.city.CityData;
import ngo.drc.address.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper extends GeneralMapper<CityData, City> {
}
