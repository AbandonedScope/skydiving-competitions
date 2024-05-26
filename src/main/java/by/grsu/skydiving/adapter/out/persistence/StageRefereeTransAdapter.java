package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.repository.StageRefereeTransJdbcRepository;
import by.grsu.skydiving.application.port.out.DeleteRefereeFromCompetitionStagePort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StageRefereeTransAdapter implements DeleteRefereeFromCompetitionStagePort {
    private final StageRefereeTransJdbcRepository stageRefereeTransJdbcRepository;

    @Override
    public int deleteRefereeFromCompetitionByCompetitionStageId(Long competitionStageId, Long refereeId) {
        return stageRefereeTransJdbcRepository.deleteByCompetitionStageIdAndRefereeId(competitionStageId, refereeId);
    }
}
