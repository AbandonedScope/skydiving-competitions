package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
import by.grsu.skydiving.application.port.in.AddCollegiumToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddCollegiumToCompetitionService implements AddCollegiumToCompetitionUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;
    private final SaveCompetitionPort saveCompetitionPort;

    @Override
    public CompetitionCollegium addCollegium(AddCollegiumCommand command) {
        Competition competition = getCompetitionUseCase.getCompetition(command.competitionId());

        CompetitionCollegium collegium = command.collegium();
        competition.addCollegium(collegium);

        competition = saveCompetitionPort.save(competition);

        return competition.getCollegium();
    }
}
