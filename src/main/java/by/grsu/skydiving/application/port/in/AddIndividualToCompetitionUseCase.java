package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.MembersOfCompetition;

public interface AddIndividualToCompetitionUseCase {
    MembersOfCompetition addIndividualToCompetition(long competitionId, CompetitionMember individual);
}
