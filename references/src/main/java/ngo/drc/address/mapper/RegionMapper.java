package ngo.drc.address.mapper;

import ngo.drc.address.batch.batchInitV1.region.RegionData;
import ngo.drc.address.entity.Region;
import ngo.drc.core.mapper.GeneralMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper extends GeneralMapper<RegionData, Region> {
}
