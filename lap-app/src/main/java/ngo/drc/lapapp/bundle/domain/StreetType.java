package ngo.drc.lapapp.bundle.domain;

import ngo.drc.lapapp.bundle.dto.BundleRow;

import java.util.ArrayList;
import java.util.List;

public class StreetType {
    private String label;
    private boolean isMultiple;
    private List<BundleRow> options = new ArrayList<>();
}
