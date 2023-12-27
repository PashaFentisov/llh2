package ngo.drc.core.security.mapper;

import ngo.drc.core.mapper.GeneralMapper;
import ngo.drc.core.security.dto.UserDto;
import ngo.drc.core.security.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GeneralMapper<UserDto, User> {
}
