package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.exception.domain.InvalidRefereeCategoryNumberException;

import java.util.Arrays;

public enum RefereeCategory {
    FIRST,
    SECOND;

    public static RefereeCategory valueOf(int categoryNumber) {
        return Arrays.stream(RefereeCategory.values())
                .filter(category -> category.ordinal() == categoryNumber)
                .findFirst()
                .orElseThrow(() -> new InvalidRefereeCategoryNumberException(categoryNumber));
    }
}
