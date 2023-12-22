package ngo.drc.micro.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentInfoDto {
    private String documentType;
    private String documentSeries;
    private Long documentNumber;
    private String documentIssuedBy;
    private OffsetDateTime documentIssuedDate;
}
