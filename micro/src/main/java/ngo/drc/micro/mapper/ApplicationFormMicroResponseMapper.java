package ngo.drc.micro.mapper;

import ngo.drc.core.mapper.GeneralMapper;
import ngo.drc.micro.dto.ApplicationFormMicroResponseDto;
import ngo.drc.micro.entity.ApplicationFormMicro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentInfoDtoMapper.class, AddressInfoDtoMapper.class, BusinessInfoDtoMapper.class, ContactInfoDtoMapper.class})
public interface ApplicationFormMicroResponseMapper extends GeneralMapper<ApplicationFormMicroResponseDto, ApplicationFormMicro> {
}
