package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.model.common.UserInfo;
import lombok.With;

public record Referee(
        @With
        Long id,
        @With
        UserInfo info,
        Integer category
) {
}
