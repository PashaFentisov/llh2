package ngo.drc.core.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateResponseDto {
    private UUID id;
    private BigDecimal rate;
    private OffsetDateTime creationDate;
}
