package by.grsu.skydiving.application.domain.model.trickRefereeing;

import java.util.Arrays;

public enum TrickType {
    TURN_1,
    TURN_2,
    TURN_3,
    TURN_4,
    BACK_LOOP_1,
    BACK_LOOP_2;

    public static TrickType of(int ordinal) {
        return Arrays.stream(TrickType.values())
                .filter(trick -> trick.ordinal() == ordinal)
                .findFirst()
                .orElseThrow();
    }
}
