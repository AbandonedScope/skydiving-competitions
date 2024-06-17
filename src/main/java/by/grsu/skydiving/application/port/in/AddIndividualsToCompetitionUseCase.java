package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.MembersOfCompetition;
import java.util.Set;

public interface AddIndividualsToCompetitionUseCase {
    MembersOfCompetition addIndividualsToCompetition(long competitionId, Set<CompetitionMember> individual);
}
