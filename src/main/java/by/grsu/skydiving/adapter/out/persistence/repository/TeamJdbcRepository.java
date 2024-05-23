package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.TeamEntity;
import by.grsu.skydiving.application.domain.model.competition.Team;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface TeamJdbcRepository extends ListCrudRepository<TeamEntity, Long> {
    @Query("""
        select *
        from team_view
        left join competition_member_detail
            on competition_member_detail.team_id = team_view.id
        left join competition
            on competition.id = competition_member_detail.competition_id
        where competition_id = :competitionId
        """)
    List<Team> findByCompetitionId(Long competitionId);

    boolean existsByName(String name);
}
