package ngo.drc;

import ngo.drc.address.mapper.GeneralMapper;
import ngo.drc.dto.MainFormSavingDto;
import ngo.drc.entity.MainForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainFormSavingMapper extends GeneralMapper<MainFormSavingDto, MainForm> {
}
