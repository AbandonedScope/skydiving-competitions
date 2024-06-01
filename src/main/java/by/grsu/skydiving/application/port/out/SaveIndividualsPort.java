package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import java.util.Set;

public interface SaveIndividualsPort {
    void saveIndividuals(Set<CompetitionMember> individuals, long competitionId);
}
