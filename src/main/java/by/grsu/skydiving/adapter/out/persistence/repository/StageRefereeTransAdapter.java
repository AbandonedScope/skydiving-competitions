package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.application.port.out.DeleteRefereeFromCompetitionStagePort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StageRefereeTransAdapter implements DeleteRefereeFromCompetitionStagePort {
    private final StageRefereeTransJdbcRepository stageRefereeTransJdbcRepository;

    @Override
    public void deleteRefereeFromCompetitionByCompetitionStageId(Long competitionStageId, Long refereeId) {
        stageRefereeTransJdbcRepository.deleteByCompetitionStageIdAndRefereeId(competitionStageId, refereeId);
    }
}
