package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import lombok.Builder;
import lombok.With;

@Builder
public record CompetitionMember(
    @With
    Long id,
    Long skydiverId,
    @With
    Long teamId,
    @With
    Long competitionId,
    boolean isJunior,
    @With
    FullName name,
    @With
    int memberNumber
) {
}
