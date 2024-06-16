package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import java.util.List;

public interface GetListOfSkydiversThatNotParticipateInCompetitionUseCase {
    List<SkydiverShortInfo> getFreeSkydiversForCompetition(Long competitionId);
}
