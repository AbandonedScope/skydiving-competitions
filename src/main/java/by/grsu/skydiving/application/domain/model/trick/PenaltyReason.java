package by.grsu.skydiving.application.domain.model.trick;

import java.util.Arrays;

public enum PenaltyReason {
    OF,
    AF,
    IS,
    NP;

    public static PenaltyReason of(Integer ordinal) {
        return ordinal == null
            ? NP
            : Arrays.stream(PenaltyReason.values())
                .filter(penalty -> penalty.ordinal() == ordinal)
                .findFirst()
                .orElseThrow();
    }
}
