package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;

public interface AddCollegiumToCompetitionUseCase {
    CompetitionCollegium addCollegium(AddCollegiumCommand command);

    record AddCollegiumCommand(
        Long competitionId,
        CompetitionCollegium collegium
    ) {
    }
}
