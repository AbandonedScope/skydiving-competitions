package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickSerieEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.RefereeingProjection;
import java.util.List;

import by.grsu.skydiving.adapter.out.persistence.entity.projection.TrickSerieProjection;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.TrickSerieShortInfoProjection;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface TrickSerieJdbcRepository extends ListCrudRepository<TrickSerieEntity, Long> {

    @Query(
        """
                        select
                            serie.id as trickSerieId,
                            serie.serie_number,
                            serie.round_number,
                            serie.penalty_reason,
                            competition.id as competitionId,
                            competition.name,
                            competition.begin_date,
                            competition.end_date,
                            competition.address
                        from trick_serie as serie
                        left join competition_member_detail as details on serie.competition_member_detail_id = details.id
                        left join competition_view as competition on details.competition_id = competition.id
                        where serie.referee_id = :refereeId and competition.status = 3;
            """
    )
    List<RefereeingProjection> getRefereeingsByRefereeId(Long refereeId);

    @Query(
            """
                    select
                            serie.id,
                            serie.serie_number,
                            serie.round_number,
                            serie.time_without_penalty,
                            serie.is_time_submitted,
                            serie.penalty_reason,
                            serie.competition_member_detail_id,
                            ccrt.referee_id,
                            ccrt.referee_number,
                            details.member_number
                            from trick_serie as serie
                    left join competition_collegium_referee_trans as ccrt on ccrt.referee_id = serie.referee_id
                    left join competition_member_detail as details on details.id = serie.competition_member_detail_id
                    where details.competition_id = :competitionId;
            """
    )
    List<TrickSerieProjection> getTrickSeriesByCompetitionId(Long competitionId);


    @Query(
            """
            select serie.id,
               serie.serie_number,
               serie.round_number,
                competition.name
                from trick_serie as serie
                    left join competition_member_detail as details on serie.competition_member_detail_id = details.id  
                    left join competition on details.competition_id = competition.id
                        where serie.id = :trickSerieId;
            """
    )
    TrickSerieShortInfoProjection getTrickSerieShortInfoByTrickSerieId(Long trickSerieId);

    @Query(
            """
               update trick_serie set penalty_reason = :penaltyReason
                    where id = :id;
                """
    )
    @Modifying
    void setPenaltyReason(Long id, Integer penaltyReason);
}
