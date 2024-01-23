package ngo.drc.core.currency.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ngo.drc.core.annotation.ValidOffsetDateTime;
import ngo.drc.core.util.CustomOffsetDateTimeDeserializer;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateSavingDto {
    @NotNull(message = "Rate can`t be null")
    @Digits(integer = 6, fraction = 3, message = "Rate should be in format 000.000")
    private BigDecimal rate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
    @ValidOffsetDateTime
    private OffsetDateTime creationDate;
}
