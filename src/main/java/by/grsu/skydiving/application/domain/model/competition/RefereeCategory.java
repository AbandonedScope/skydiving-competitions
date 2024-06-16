package by.grsu.skydiving.application.domain.model.competition;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RefereeCategory {
    SPORT_REFEREE(0, "Судья по спорту"),
    SPORT_REFEREE_OF_FIRST_CATEGORY(1, "Судья по спорту первой категории"),
    SPORT_REFEREE_OF_NATIONAL_CATEGORY(2, "Судья по спорту национальной категории"),
    SPORT_REFEREE_OF_HIGHEST_NATIONAL_CATEGORY(3, "Судья по спорту высшей национальной категории");

    private final int id;
    private final String description;

    public static RefereeCategory of(int id) {
        return Arrays.stream(RefereeCategory.values())
            .filter(category -> category.id == id)
            .findFirst()
            .orElseThrow();
    }
}
