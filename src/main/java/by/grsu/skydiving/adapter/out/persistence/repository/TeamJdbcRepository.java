package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.TeamEntity;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface TeamJdbcRepository extends ListCrudRepository<TeamEntity, Long> {
    @Query("""
        select
            team_view.id,
            team_view.name
        from team_view
        left join competition_member_detail
            on competition_member_detail.team_id = team_view.id
        left join competition
            on competition.id = competition_member_detail.competition_id
        where competition_id = :competitionId
        """)
    List<TeamEntity> findByCompetitionId(Long competitionId);

    boolean existsByName(String name);
}
