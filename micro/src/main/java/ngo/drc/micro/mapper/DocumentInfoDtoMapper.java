package ngo.drc.micro.mapper;

import ngo.drc.address.mapper.GeneralMapper;
import ngo.drc.micro.dto.DocumentInfoDto;
import ngo.drc.micro.entity.DocumentInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentInfoDtoMapper extends GeneralMapper<DocumentInfoDto, DocumentInfo> {
}
