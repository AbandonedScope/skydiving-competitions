package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieOfSkydiver;
import java.util.List;

public interface GetTrickSeriesByCompetitionIdUseCase {
    List<TrickSerieOfSkydiver> getByCompetitionId(Long competitionId);
}
