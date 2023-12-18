package ngo.drc.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentInfoDto {
    private String documentType;
    private String documentSeries;
    private Long documentNumber;
    private String documentIssuedBy;
    private OffsetDateTime documentIssuedDate;
}
