package by.grsu.skydiving.common.knowledge;

import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class KnowledgeBaseUtils {
    private static final String SPORT_RANKS = "sportRanks";
    private static final String REFEREE_CATEGORIES = "refereeCategory";


    public Map<String, List<EnumModel>> getEnums() {
        Map<String, List<EnumModel>> enums = HashMap.newHashMap(3);

        enums.put(SPORT_RANKS, getSportRanks());
        enums.put(REFEREE_CATEGORIES, getRefereeCategories());

        return enums;
    }

    private List<EnumModel> getRefereeCategories() {
        return Arrays.stream(RefereeCategory.values())
            .map(value -> EnumModel.builder()
                .id(value.getId())
                .name(value.name())
                .description(value.getDescription())
                .build())
            .toList();
    }

    private List<EnumModel> getSportRanks() {
        return Arrays.stream(SportRank.values())
            .map(value -> EnumModel.builder()
                .id(value.getId())
                .name(value.name())
                .description(value.getDescription())
                .build())
            .toList();
    }
}
