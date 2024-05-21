package by.grsu.skydiving.application.domain.model.competition;

import java.util.Arrays;

public enum RefereeCategory {
    FIRST,
    SECOND;

    public static RefereeCategory of(int ordinal) {
        return Arrays.stream(RefereeCategory.values())
                .filter(category -> category.ordinal() == ordinal)
                .findFirst()
                .orElseThrow();
    }
}
