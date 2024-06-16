package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickAttemptEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TrickAttemptJdbcRepository extends ListCrudRepository<TrickAttemptEntity, Long> {
    @Query(
            """
            select attempt.id,
            attempt.trick_serie_id,
            attempt.trick_type,
            attempt.arrow_penalty,
            attempt.d_penalty,
            attempt.s_penalty,
            attempt.minus_penalty,
            attempt.plus_minus_penalty
            from trick_attempt as attempt
            left join trick_serie as serie on serie.id = attempt.trick_serie_id
            left join competition_member_detail as details on  details.id = serie.competition_member_detail_id
            where details.competition_id = :competitionId
            """
    )
    List<TrickAttemptEntity> getTrickAttemptByCompetitionId(Long competitionId);
}
