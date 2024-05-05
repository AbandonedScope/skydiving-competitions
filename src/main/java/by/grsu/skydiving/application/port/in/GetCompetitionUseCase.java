package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.Competition;

public interface GetCompetitionUseCase {
    Competition getCompetition(Long competitionId);
}
