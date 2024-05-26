package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import java.util.List;
import java.util.Set;

public record MembersOfCompetitionResponse(
    long competitionId,
    List<TeamWithMembersResponse> teams,
    Set<CompetitionMember> individuals
) {
}
