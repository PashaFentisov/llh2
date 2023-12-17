package ngo.drc.dto;

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
    private String documentName;
    private String documentSeries;
    private Long documentNumber;
    private String documentIssuedBy;
    private OffsetDateTime documentIssuedDate;
}
