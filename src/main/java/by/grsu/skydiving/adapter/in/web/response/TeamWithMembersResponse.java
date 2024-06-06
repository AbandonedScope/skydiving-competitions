package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import java.util.Set;

public record TeamWithMembersResponse(
    long id,
    String name,
    Set<CompetitionMember> members
) {
}
