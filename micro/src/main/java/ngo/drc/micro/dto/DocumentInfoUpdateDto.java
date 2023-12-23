package ngo.drc.micro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import lombok.*;
import ngo.drc.core.annotation.ValidOffsetDateTime;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
    @ValidOffsetDateTime
    private OffsetDateTime documentIssuedDate;
}
