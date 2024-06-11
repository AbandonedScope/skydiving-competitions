package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.RefereeEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.CollegiumRefereeProjection;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.RefereeProjection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface RefereeJdbcRepository extends ListCrudRepository<RefereeEntity, Long> {
    @Query("""
        select referee.id,
               referee.category,
               user_info.first_name,
               user_info.second_name,
               user_info.patronymic,
               trans.competition_collegium_id,
               trans.is_main_collegium,
               trans.referee_number,
               trans.work_performed
        from competition_collegium_referee_trans as trans
                 left join referee on trans.referee_id = referee.id
                 left join user_info on referee.id = user_info.id
        where competition_collegium_id in (select cc.id
                                           from competition_collegium cc
                                           where cc.competition_id = :competitionId)
        """)
    Optional<List<CollegiumRefereeProjection>> findByCompetitionId(Long competitionId);

    @Modifying
    @Query("""
        update referee
        set is_deleted = true
        where referee.id = :refereeId
        """)
    int deleteRefereeByRefereeId(Long refereeId);

    List<RefereeProjection> filter(Map<String, Object> filters, long limit, long offset);

    Long countFiltered(Map<String, Object> filters);
}
