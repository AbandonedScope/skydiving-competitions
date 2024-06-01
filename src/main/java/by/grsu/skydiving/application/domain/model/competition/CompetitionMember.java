package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import lombok.Builder;
import lombok.With;

@Builder
public record CompetitionMember(
    Long id,
    Long skydiverId,
    @With
    Long teamId,
    Long competitionId,
    boolean isJunior,
    @With
    FullName name,
    int memberNumber
) {
}
