package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import java.util.Optional;

public interface GetCompetitionMemberPort {
    Optional<CompetitionMember> getByCompetitionIdAndSkydiverId(long competitionId, long skydiverId);
}
