package ngo.drc.micro.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import lombok.*;
import ngo.drc.core.util.CustomOffsetDateTimeDeserializer;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentInfoUpdateDto {
    private String documentType;
    private String documentSeries;
    @Min(value = 0, message = "Document number can`t be negative")
    private Long documentNumber;
    private String documentIssuedBy;
    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
    private OffsetDateTime documentIssuedDate;
}
