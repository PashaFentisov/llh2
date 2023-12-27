package ngo.drc.address.mapper;

import ngo.drc.address.batch.batchInitV1.hromada.HromadaData;
import ngo.drc.address.entity.Hromada;
import ngo.drc.core.mapper.GeneralMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HromadaMapper extends GeneralMapper<HromadaData, Hromada> {
}
