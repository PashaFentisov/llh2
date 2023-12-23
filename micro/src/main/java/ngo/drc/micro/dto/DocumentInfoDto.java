package ngo.drc.micro.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentInfoDto {
    @NotBlank(message = "Document type can`t be empty or null")
    private String documentType;
    @NotBlank(message = "Document series can`t be empty or null")
    private String documentSeries;
    @NotNull(message = "Document number can`t be null")
    @Min(value = 0, message = "Document number can`t be negative")
    private Long documentNumber;
    @NotBlank(message = "Document issued by can`t be empty or null")
    private String documentIssuedBy;
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
//    @ValidOffsetDateTime
    private OffsetDateTime documentIssuedDate;
}
