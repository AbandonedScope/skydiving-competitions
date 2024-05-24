package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import lombok.Builder;

@Builder
public record CompetitionMember(
        Long skydiverId,
        FullName name,
        int memberNumber
) {
}
