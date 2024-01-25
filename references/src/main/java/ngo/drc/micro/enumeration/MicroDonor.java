package ngo.drc.micro.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MicroDonor {
    SDC1("SDC1", 300),  //todo fix numbers
    SDC2019("SDC2019", 0),
    DFID1("DFID1", 60),
    DFID2("DFID2", 150),
    DFID3("DFID3", 90),
    DFID2020("DFID 2020", 0),
    SDC2("SDC 2", 300),
    DFID2021("DFID 2021", 0);
    private final String donorName;
    private final int maxPossibleAmountOfApplications;

    public static MicroDonor fromString(String text) {
        if (text == null) {
            return null;
        }
        for (MicroDonor donor : MicroDonor.values()) {
            if (donor.donorName.equalsIgnoreCase(text)) {
                return donor;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
