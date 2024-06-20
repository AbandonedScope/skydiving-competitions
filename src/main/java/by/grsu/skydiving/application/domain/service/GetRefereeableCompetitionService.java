package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.CompetitionCanNotBeRefereedException;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetRefereeableCompetitionUseCase;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetRefereeableCompetitionService implements GetRefereeableCompetitionUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;

    @Override
    public Competition getCompetitionThatCanBeRefereed(long competitionId) {
        Competition competition = getCompetitionUseCase.getCompetition(competitionId);

        if (!competition.canBeRefereed()) {
            throw new CompetitionCanNotBeRefereedException();
        }

        return competition;
    }
}
