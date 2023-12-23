package ngo.drc.micro.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentInfo {
    private String documentType;
    private String documentSeries;
    private Long documentNumber;
    private String documentIssuedBy;
    private OffsetDateTime documentIssuedDate;

    public DocumentInfo(DocumentInfo documentInfo) {
        this.documentType = documentInfo.getDocumentType();
        this.documentSeries = documentInfo.getDocumentSeries();
        this.documentNumber = documentInfo.getDocumentNumber();
        this.documentIssuedBy = documentInfo.getDocumentIssuedBy();
        this.documentIssuedDate = documentInfo.getDocumentIssuedDate();
    }
}
