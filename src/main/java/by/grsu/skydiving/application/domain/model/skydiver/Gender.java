package by.grsu.skydiving.application.domain.model.skydiver;

import java.util.Arrays;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender of(int ordinal) {
        return Arrays.stream(Gender.values())
            .filter(gender -> gender.ordinal() == ordinal)
            .findFirst()
            .orElseThrow();
    }
}
