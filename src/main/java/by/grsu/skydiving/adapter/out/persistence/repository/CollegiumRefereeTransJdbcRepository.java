package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CollegiumRefereeTransEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface CollegiumRefereeTransJdbcRepository extends ListCrudRepository<CollegiumRefereeTransEntity, Long> {

    @Modifying
    @Query("""
        delete
        from competition_collegium_referee_trans as comStTrans
        where comStTrans.referee_id = :refereeId
          and comStTrans.competition_collegium_id
            in (select competition_collegium.id
                from competition_collegium
                         join public.competition
                              on competition.id = competition_collegium.competition_id
                where competition.id = :competitionId);
        """)
    int deleteByCompetitionIdAndRefereeId(Long competitionId, Long refereeId);

    @Modifying
    @Query("""
        delete from competition_collegium_referee_trans as comStTrans
        where  comStTrans.competition_collegium_id = :competitionCollegiumId
        """)
    void deleteAllByCompetitionCollegiumId(long competitionCollegiumId);
}
