package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStage;
import by.grsu.skydiving.application.port.in.AddStageToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddStageToCompetitionService implements AddStageToCompetitionUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;
    private final SaveCompetitionPort saveCompetitionPort;

    @Override
    public CompetitionStage addStage(AddStageCommand command) {
        Competition competition = getCompetitionUseCase.getCompetition(command.competitionId());

        CompetitionStage stage = command.stage();
        competition.addStage(stage);

        competition = saveCompetitionPort.save(competition);

        return competition.getStage(stage.number());
    }
}
