package ngo.drc.micro.mapper;

import ngo.drc.core.mapper.GeneralMapper;
import ngo.drc.micro.dto.ApplicationFormMicroSavingDto;
import ngo.drc.micro.entity.ApplicationFormMicro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentInfoDtoMapper.class, AddressInfoDtoMapper.class, BusinessInfoDtoMapper.class, ContactInfoDtoMapper.class})
public interface ApplicationFormMicroSavingMapper extends GeneralMapper<ApplicationFormMicroSavingDto, ApplicationFormMicro> {
}
