package by.grsu.skydiving.application.port.in;

import java.time.LocalDate;

public interface CreateCompetitionJumpingUseCase {
    void create(CreateCompetitionJumpingCommand command);

    record CreateCompetitionJumpingCommand(
        long competitionId,
        int accuracy,
        int memberNumber,
        int jumpingNumber,
        LocalDate performanceDate
    ) {
    }
}
