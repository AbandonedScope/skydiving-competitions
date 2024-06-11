package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.in.UpdateCollegiumOfCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateCollegiumOfCompetitionService implements UpdateCollegiumOfCompetitionUseCase {
    private final GetUpdatableCompetitionUseCase getUpdatableCompetitionUseCase;
    private final SaveCompetitionPort saveCompetitionPort;

    @Override
    public CompetitionCollegium update(UpdateCollegiumCommand command) {
        long competitionId = command.competitionId();
        Competition competition =
            getUpdatableCompetitionUseCase.getCompetitionThatCanBeUpdated(competitionId);

        competition.updateCollegium(command.collegium());

        competition = saveCompetitionPort.save(competition);

        return competition.getCollegium();
    }
}
