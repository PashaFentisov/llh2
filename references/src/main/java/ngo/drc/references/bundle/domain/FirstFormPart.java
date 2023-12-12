package ngo.drc.references.bundle.domain;

import lombok.Data;
import ngo.drc.references.bundle.dto.BundleRow;

import java.util.ArrayList;
import java.util.List;
@Data
public abstract class FirstFormPart {
    private String label;
    private boolean isMultiple;
    private List<BundleRow> options = new ArrayList<>();
}
