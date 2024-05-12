package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.RefereeEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.CollegiumRefereeProjection;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface RefereeJdbcRepository extends ListCrudRepository<RefereeEntity, Long> {
    @Query("""
            select referee.id, referee.category,
            user_info.first_name, user_info.second_name, user_info.patronymic,
            trans.competition_stage_id, trans.is_main_collegium, trans.work_performed
            from competition_stage_referee_trans as trans
                left join referee on trans.referee_id = referee.id
                left join user_info on referee.user_info_id = user_info.id
            where competition_stage_id = :competitionStageId
            """)
    Optional<List<CollegiumRefereeProjection>> findByCompetitionId(Long refereeId);

    @Modifying
    @Query("""
           update referee
           set is_deleted = true
           where referee.id = :refereeId
           """)
    int deleteRefereeByRefereeId(Long refereeId);
}
