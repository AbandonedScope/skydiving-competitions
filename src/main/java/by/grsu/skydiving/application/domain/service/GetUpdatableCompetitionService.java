package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.TryToUpdateImmutableCompetitionException;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUpdatableCompetitionService implements GetUpdatableCompetitionUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;

    @Override
    public Competition getCompetitionThatCanBeUpdated(long competitionId) {
        Competition competition = getCompetitionUseCase.getCompetition(competitionId);
        if (!competition.canBeUpdated()) {
            throw new TryToUpdateImmutableCompetitionException("Competition can't be updated");
        }

        return competition;
    }
}
