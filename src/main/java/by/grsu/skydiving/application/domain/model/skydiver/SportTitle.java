package by.grsu.skydiving.application.domain.model.skydiver;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SportTitle {
    MASTER_OF_SPORT_OF_REPUBLIC_OF_BELARUS_OF_INTERNATIONAL_CLASS(1,
        "Мастер спорта Республики Беларусь международного класса"),
    MASTER_OF_SPORT_OF_REPUBLIC_OF_BELARUS(2, "Мастер спорта Республики Беларусь"),
    MASTER_OF_SPORT_OF_REPUBLIC_OF_BELARUS_OF_INTERNATIONAL_CLASS_AMONG_DISABLED(3,
        "Мастер спорта Республики Беларусь международного класса среди инвалидов"),
    MASTER_OF_SPORT_OF_REPUBLIC_OF_BELARUS_AMONG_DISABLED(4, "Мастер спорта Республики Беларусь среди инвалидов");

    private final int id;
    private final String russianName;

    public static SportTitle of(int id) {
        return Arrays.stream(SportTitle.values())
            .filter(title -> title.id == id)
            .findFirst()
            .orElseThrow();
    }
}
