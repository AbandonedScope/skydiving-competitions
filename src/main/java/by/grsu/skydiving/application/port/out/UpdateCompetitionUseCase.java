package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import java.time.LocalDate;
import lombok.Builder;

public interface UpdateCompetitionUseCase {
    Competition updateCompetition(UpdateCompetitionCommand command);

    @Builder
    record UpdateCompetitionCommand(
        Long id,
        String name,
        LocalDate beginDate,
        LocalDate endDate,
        String place
    ) {
    }
}
