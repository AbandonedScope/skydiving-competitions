package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickSerieEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.RefereeingProjection;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TrickSerieJdbcRepository extends ListCrudRepository<TrickSerieEntity, Long> {

    @Query(
            """
            select
                serie.id as trickSerieId,
                serie.serie_number,
                serie.round_number,
                competition.id as competitionId,
                competition.name,
                competition.begin_date,
                competition.end_date,
                competition.address,
                competition.number_of_stages
            from trick_serie as serie
            left join competition_member_detail as details on serie.competition_member_detail_id = details.id
            left join competition_view as competition on details.competition_id = competition.id
            where serie.referee_id = :refereeId and competition.status = 3;
"""
    )
    List<RefereeingProjection> getRefereeingsByRefereeId(Long refereeId);
}
