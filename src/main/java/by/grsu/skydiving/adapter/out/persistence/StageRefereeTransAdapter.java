package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.repository.CollegiumRefereeTransJdbcRepository;
import by.grsu.skydiving.application.port.out.DeleteRefereeFromCompetitionStagePort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StageRefereeTransAdapter implements DeleteRefereeFromCompetitionStagePort {
    private final CollegiumRefereeTransJdbcRepository collegiumRefereeTransJdbcRepository;

    @Override
    public int deleteRefereeFromCompetitionByCompetitionCollegiumId(Long competitionCollegiumId, Long refereeId) {
        return collegiumRefereeTransJdbcRepository.deleteByCompetitionCollegiumIdAndRefereeId(competitionCollegiumId,
            refereeId);
    }
}
