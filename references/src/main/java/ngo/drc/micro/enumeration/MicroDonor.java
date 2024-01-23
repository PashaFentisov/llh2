package ngo.drc.micro.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MicroDonor {
    NONE("none"),
    SDC1("SDC1"),
    SDC2019("SDC2019"),
    DFID1("DFID1"),
    DFID2("DFID2"),
    DFID3("DFID3"),
    DFID2020("DFID 2020"),
    SDC2("SDC 2"),
    DFID2021("DFID 2021");
    private final String donorName;
}
