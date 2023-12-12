package ngo.drc.references.bundle.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ngo.drc.references.bundle.dto.BundleRow;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HomeLeavingReason {
    private String label;
    private boolean isMultiple;
    private List<BundleRow> options = new ArrayList<>();
}
