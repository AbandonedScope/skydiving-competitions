package by.grsu.skydiving.application.domain.model.trickRefereeing;

import java.util.Arrays;

public enum PenaltyReason {
    OF,
    AF,
    IS,
    NP;

    public static PenaltyReason of(int ordinal) {
        return Arrays.stream(PenaltyReason.values())
                .filter(penalty -> penalty.ordinal() == ordinal)
                .findFirst()
                .orElseThrow();
    }
}
