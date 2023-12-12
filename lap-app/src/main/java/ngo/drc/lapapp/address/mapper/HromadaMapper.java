package ngo.drc.lapapp.address.mapper;

import jakarta.persistence.GeneratedValue;
import ngo.drc.lapapp.address.batch.batchInitV1.hromada.HromadaData;
import ngo.drc.lapapp.address.entity.Hromada;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HromadaMapper extends GeneralMapper<HromadaData, Hromada>{
}
