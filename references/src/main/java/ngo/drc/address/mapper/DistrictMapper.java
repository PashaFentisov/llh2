package ngo.drc.address.mapper;

import ngo.drc.address.batch.batchInitV1.district.DistrictData;
import ngo.drc.address.entity.District;
import ngo.drc.core.mapper.GeneralMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DistrictMapper extends GeneralMapper<DistrictData, District> {
}
