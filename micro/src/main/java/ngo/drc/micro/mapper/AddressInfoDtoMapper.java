package ngo.drc.micro.mapper;

import ngo.drc.core.mapper.GeneralMapper;
import ngo.drc.micro.dto.AddressInfoDto;
import ngo.drc.micro.entity.AddressInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressInfoDtoMapper extends GeneralMapper<AddressInfoDto, AddressInfo> {
}
