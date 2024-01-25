package ngo.drc.core.currency.mapper;

import ngo.drc.core.currency.dto.CurrencyRateResponseDto;
import ngo.drc.core.currency.entity.CurrencyRate;
import ngo.drc.core.mapper.GeneralMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyRateResponseMapper extends GeneralMapper<CurrencyRateResponseDto, CurrencyRate> {
}
