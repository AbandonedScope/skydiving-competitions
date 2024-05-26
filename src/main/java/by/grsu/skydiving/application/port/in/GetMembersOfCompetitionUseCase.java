package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.MembersOfCompetition;

public interface GetMembersOfCompetitionUseCase {
    MembersOfCompetition getMembersOfCompetition(Long competitionId);
}
