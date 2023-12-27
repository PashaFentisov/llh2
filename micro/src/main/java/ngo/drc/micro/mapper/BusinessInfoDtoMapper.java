package ngo.drc.micro.mapper;

import ngo.drc.core.mapper.GeneralMapper;
import ngo.drc.micro.dto.BusinessInfoDto;
import ngo.drc.micro.entity.BusinessInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessInfoDtoMapper extends GeneralMapper<BusinessInfoDto, BusinessInfo> {
}
