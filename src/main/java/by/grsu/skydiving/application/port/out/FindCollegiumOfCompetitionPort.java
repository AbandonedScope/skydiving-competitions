package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
import java.util.Optional;

public interface FindCollegiumOfCompetitionPort {
    Optional<CompetitionCollegium> findByCompetitionId(long competitionId);
}
