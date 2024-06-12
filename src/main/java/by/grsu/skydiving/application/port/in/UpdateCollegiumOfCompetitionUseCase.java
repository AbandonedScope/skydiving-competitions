package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;

public interface UpdateCollegiumOfCompetitionUseCase {
    CompetitionCollegium update(UpdateCollegiumCommand command);

    record UpdateCollegiumCommand(
        Long competitionId,
        CompetitionCollegium collegium
    ) {
    }
}
