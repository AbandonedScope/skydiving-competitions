package by.grsu.skydiving.application.domain.model.competition;

import java.util.List;
import java.util.Set;
import lombok.Builder;

@Builder
public record MembersOfCompetition(
    List<Team> teams,
    Set<CompetitionMember> individuals
) {
}
