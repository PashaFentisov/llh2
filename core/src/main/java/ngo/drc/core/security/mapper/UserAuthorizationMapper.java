package ngo.drc.core.security.mapper;


import ngo.drc.core.mapper.GeneralMapper;
import ngo.drc.core.security.dto.UserAuthorizationDto;
import ngo.drc.core.security.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAuthorizationMapper extends GeneralMapper<UserAuthorizationDto, User> {
}
