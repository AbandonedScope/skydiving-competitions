package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import java.util.Optional;

public interface FindRefereesPort {
    Optional<RefereeGroups> findRefereesByCompetitionCollegiumId(Long competitionCollegiumId);
}
