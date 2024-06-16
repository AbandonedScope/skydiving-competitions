package by.grsu.skydiving.application.domain.model.skydiver;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SportRank {
    CANDIDATE_FOR_MASTER_OF_SPORT(1, "Кандидат в мастера спорта (КМС)"),
    FIRST_DEGREE(2, "I разряд"),
    SECOND_DEGREE(3, "II разряд"),
    THIRD_DEGREE(4, "III разряд"),
    FIRST_JUNIOR_DEGREE(5, "I юношеский разряд"),
    SECOND_JUNIOR_DEGREE(6, "II юношеский разряд"),
    THIRD_JUNIOR_DEGREE(7, "III юношеский разряд"),
    CANDIDATE_FOR_MASTER_OF_SPORT_AMONG_DISABLED(8, "Кандидат в мастера спорта среди инвалидов"),
    FIRST_DEGREE_AMONG_DISABLED(9, "I разряд среди инвалидов"),
    SECOND_DEGREE_AMONG_DISABLED(10, "II разряд среди инвалидов"),
    THIRD_DEGREE_AMONG_DISABLED(11, "III разряд среди инвалидов"),
    FIRST_JUNIOR_DEGREE_AMONG_DISABLED(12, "I юношеский разряд среди инвалидов"),
    SECOND_JUNIOR_DEGREE_AMONG_DISABLED(13, "II юношеский разряд среди инвалидов"),
    THIRD_JUNIOR_DEGREE_AMONG_DISABLED(14, "III юношеский разряд среди инвалидов"),

    MASTER_OF_SPORT_OF_REPUBLIC_OF_BELARUS_OF_INTERNATIONAL_CLASS(15,
        "Мастер спорта Республики Беларусь международного класса"),
    MASTER_OF_SPORT_OF_REPUBLIC_OF_BELARUS(16, "Мастер спорта Республики Беларусь"),
    MASTER_OF_SPORT_OF_REPUBLIC_OF_BELARUS_OF_INTERNATIONAL_CLASS_AMONG_DISABLED(17,
        "Мастер спорта Республики Беларусь международного класса среди инвалидов"),
    MASTER_OF_SPORT_OF_REPUBLIC_OF_BELARUS_AMONG_DISABLED(18, "Мастер спорта Республики Беларусь среди инвалидов");;

    private final int id;
    private final String russianName;

    public static SportRank of(int id) {
        return Arrays.stream(SportRank.values())
            .filter(rank -> rank.id == id)
            .findFirst()
            .orElseThrow();
    }
}
