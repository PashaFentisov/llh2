package ngo.drc.micro.mapper;

import ngo.drc.address.mapper.GeneralMapper;
import ngo.drc.micro.dto.ContactInfoDto;
import ngo.drc.micro.entity.ContactInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactInfoDtoMapper extends GeneralMapper<ContactInfoDto, ContactInfo> {
}
