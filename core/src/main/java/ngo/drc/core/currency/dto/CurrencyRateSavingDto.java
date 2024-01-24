package ngo.drc.core.currency.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateSavingDto {
    @NotNull(message = "Rate can`t be null")
    @Digits(integer = 6, fraction = 3, message = "Rate should be in format 000.000")
    private BigDecimal rate;
}
