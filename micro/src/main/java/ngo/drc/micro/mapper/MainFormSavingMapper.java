package ngo.drc.micro.mapper;

import ngo.drc.address.mapper.GeneralMapper;
import ngo.drc.micro.dto.MainFormSavingDto;
import ngo.drc.micro.entity.MainForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainFormSavingMapper extends GeneralMapper<MainFormSavingDto, MainForm> {
}
