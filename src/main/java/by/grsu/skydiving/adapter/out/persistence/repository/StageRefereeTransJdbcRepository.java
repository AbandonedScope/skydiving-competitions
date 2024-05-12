package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.StageRefereeTransEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface StageRefereeTransJdbcRepository extends ListCrudRepository<StageRefereeTransEntity, Long> {

    @Modifying
    @Query("""
            delete from competition_stage_referee_trans as comStTrans
            where comStTrans.referee_id = :refereeId and comStTrans.competition_stage_id = :competitionStageId
            """)
    void deleteByCompetitionStageIdAndRefereeId(Long competitionStageId, Long refereeId);
}
