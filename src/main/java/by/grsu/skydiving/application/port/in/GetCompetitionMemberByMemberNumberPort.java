package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import java.util.Optional;

public interface GetCompetitionMemberByMemberNumberPort {
    Optional<CompetitionMember> getByCompetitionIdAndMemberNumber(long competitionId, int memberNumber);
}
