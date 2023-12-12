package ngo.drc.lapapp.bundle.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ngo.drc.lapapp.bundle.dto.AboutProgramDto;
import ngo.drc.lapapp.bundle.dto.BundleRow;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NegativeSurvivalStrategy {
    private String label;
    private boolean isMultiple;
    private List<BundleRow> options = new ArrayList<>();
}
