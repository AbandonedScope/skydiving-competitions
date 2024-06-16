package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;

public interface AddCollegiumRefereeToCompetitionUseCase {
    CompetitionCollegium addCollegiumReferee(AddCollegiumRefereeCommand command);

    record AddCollegiumRefereeCommand(
        Long competitionId,
        Long refereeId,
        Integer refereeNumber,
        String workPerformed,
        boolean isMainCollegium
    ) {
    }
}
