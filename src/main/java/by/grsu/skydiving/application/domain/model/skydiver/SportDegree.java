package by.grsu.skydiving.application.domain.model.skydiver;

import java.util.Arrays;

public enum SportDegree {
    KMS;

    public static SportDegree of(int ordinal) {
        return Arrays.stream(SportDegree.values())
            .filter(degree -> degree.ordinal() == ordinal)
            .findFirst()
            .orElseThrow();
    }
}
