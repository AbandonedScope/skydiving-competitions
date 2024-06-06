package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.skydiver.Address;
import java.time.LocalDate;
import lombok.Builder;

public interface InitiateCompetitionUseCase {
    Competition initiateCompetition(InitiateCompetitionCommand command);

    @Builder
    record InitiateCompetitionCommand(
        String name,
        LocalDate beginDate,
        LocalDate endDate,
        Address placeOfCompetition,
        Integer numberOfStages
    ) {
    }
}
