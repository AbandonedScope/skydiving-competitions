package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.skydiver.Place;
import lombok.Builder;

import java.time.LocalDate;

public interface CreateInitialCompetitionUseCase {
    Competition createInitial(CreateCompetitionCommand command);

    @Builder
    record CreateCompetitionCommand(
            String name,
            LocalDate beginDate,
            LocalDate endDate,
            Place placeOfCompetition,
            int numberOfStages
    ) {
    }
}
