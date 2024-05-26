package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionMemberDetailsEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.CompetitionMemberDetailsWithFullNameAndTeamName;
import java.util.List;

import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface CompetitionMemberDetailsJdbcRepository extends ListCrudRepository<CompetitionMemberDetailsEntity, Long> {
    List<CompetitionMemberDetailsEntity> findByTeamId(Long teamId);

    @Query("""
            select
                cmd.id,
                cmd.is_junior,
                cmd.team_id,
                t.name as team_name,
                cmd.skydiver_id,
                u.first_name,
                u.second_name,
                u.patronymic,
                cmd.competition_id,
                cmd.member_number,
                (cmd.team_id is null) as is_individual
            from competition_member_detail cmd
            join  skydiver_view as s on cmd.skydiver_id = s.id
            join user_info_view as u on s.id = u.id
            left join team_view as t on cmd.team_id = t.id
            where cmd.competition_id = :competitionId
            
""")
    List<CompetitionMemberDetailsWithFullNameAndTeamName> findByCompetitionId(Long competitionId);

    List<CompetitionMemberDetailsEntity> findByTeamIdAndCompetitionId(Long teamId, Long competitionId);
    CompetitionMemberDetailsEntity findBySkydiverIdAndCompetitionId(Long skydiverId, Long competitionId);
}