package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeing;

public interface AddTrickRefereeingUseCase {
    TrickRefereeing addTrickRefereeing(AddTrickRefereeingCommand command);

    record AddTrickRefereeingCommand(
        Long competitionId,
        Long skydiverId,
        Integer serieNumber,
        Integer roundNumber
    ) {
    }
}
