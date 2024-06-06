package by.grsu.skydiving.application.domain.model.trickRefereeing;

import java.util.Arrays;

public enum PenaltyType {
    ARROW_PENALTY,
    D_PENALTY,
    S_PENALTY,
    MINUS_PENALTY,
    PLUS_MINUS_PENALTY;

    public static PenaltyType of(int ordinal) {
        return Arrays.stream(PenaltyType.values())
            .filter(penalty -> penalty.ordinal() == ordinal)
            .findFirst()
            .orElseThrow();
    }
}
