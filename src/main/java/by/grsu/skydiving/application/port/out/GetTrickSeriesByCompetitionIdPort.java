package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieOfSkydiver;
import java.util.List;

public interface GetTrickSeriesByCompetitionIdPort {
    List<TrickSerieOfSkydiver> getByCompetitionId(Long competitionId);
}
