package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.CompetitionStage;

public interface AddStageToCompetitionUseCase {
    CompetitionStage addStage(AddStageCommand command);

    record AddStageCommand(
            Long competitionId,
            CompetitionStage stage
    ) {
    }
}
