package ngo.drc.lapapp.bundle.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ngo.drc.lapapp.bundle.dto.BundleRow;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Getter
public class AboutProgram{
    private String label;
    private boolean isMultiple;
    private List<BundleRow> options = new ArrayList<>();
    //todo string field for other option???????
}
