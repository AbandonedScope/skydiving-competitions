package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import lombok.With;

public record Referee(
    @With
    Long id,
    FullName name,
    RefereeCategory category
) {
}
