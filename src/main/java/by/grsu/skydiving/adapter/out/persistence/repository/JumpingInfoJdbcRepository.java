package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.JumpingInfoEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface JumpingInfoJdbcRepository extends ListCrudRepository<JumpingInfoEntity, Long> {

    Optional<JumpingInfoEntity> findById(long id);

    List<JumpingInfoEntity> findByCompetitionMemberDetailsIdOrderByNumberAsc(long competitionMemberDetailsId);

    @Query("""
            select *
            from jumping
            where jumping.competition_member_detail_id in (
                select id
                from competition_member_detail
                where competition_member_detail.competition_id = :competitionId
            )
        """)
    List<JumpingInfoEntity> findByCompetitionId(long competitionId);

    @Query("""
        SELECT count(*) + 1
        FROM jumping
        WHERE jumping.competition_member_detail_id in (select id
                                                       from competition_member_detail
                                                       where competition_id = :competitionId
                                                         and competition_member_detail.skydiver_id = :skydiverId)
        """)
    int getNextNumberOfJumping(long competitionId, long skydiverId);
}
