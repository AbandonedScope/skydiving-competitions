package by.grsu.skydiving.application.domain.model.competition;

import java.util.List;
import java.util.Set;

public record MembersOfCompetition(
    List<Team> teams,
    Set<CompetitionMember> individuals
) {
}
