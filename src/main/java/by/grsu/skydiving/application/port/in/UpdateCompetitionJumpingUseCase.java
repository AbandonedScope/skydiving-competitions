package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import java.time.LocalDate;

public interface UpdateCompetitionJumpingUseCase {
    JumpingInfo update(UpdateCompetitionJumpingCommand command);

    record UpdateCompetitionJumpingCommand(
        long competitionId,
        long skydiverId,
        long id,
        int accuracy,
        int memberNumber,
        int jumpingNumber,
        LocalDate performanceDate
    ) {
    }
}
