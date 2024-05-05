package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.model.common.UserInfo;

public record Referee(
        Long id,
        UserInfo info,
        String category
) {
}
