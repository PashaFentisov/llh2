package ngo.drc.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
public abstract class MicroMainFormPart {
    private String label;
    private boolean isMultiple;
    private List<BundleRow> options = new ArrayList<>();
}
