package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.port.in.DeleteIndividualFromCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteIndividualFromCompetitionService implements DeleteIndividualFromCompetitionUseCase {
    private final GetUpdatableCompetitionUseCase getUpdatableCompetitionUseCase;
    private final SaveCompetitionPort saveCompetitionPort;

    @Override
    public void delete(long competitionId, long individualId) {
        Competition competition = getUpdatableCompetitionUseCase.getCompetitionThatCanBeUpdated(competitionId);

        competition.removeIndividual(individualId);

        saveCompetitionPort.save(competition);
    }
}
