package by.grsu.skydiving.adapter.out.persistence;

import static generated.Tables.COMPETITION;
import static generated.Tables.COMPETITION_MEMBER_DETAIL;
import static generated.Tables.TRICK_ATTEMPT;
import static generated.Tables.TRICK_SERIE;
import static org.jooq.impl.DSL.avg;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionMemberDetailsEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.TrickSerieEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.RefereeingProjection;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.TrickSerieShortInfoProjection;
import by.grsu.skydiving.adapter.out.persistence.mapper.AcrobaticsShortInfoRecordMapper;
import by.grsu.skydiving.adapter.out.persistence.mapper.TrickSerieMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.CompetitionMemberDetailsJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.TrickSerieJdbcRepository;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.pivot.AcrobaticsShortInfo;
import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trick.Refereeing;
import by.grsu.skydiving.application.domain.model.trick.TrickRefereeing;
import by.grsu.skydiving.application.domain.model.trick.TrickRefereeingFullInfo;
import by.grsu.skydiving.application.domain.model.trick.TrickSerie;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieShortInfo;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieTime;
import by.grsu.skydiving.application.port.out.FindTrickSerieForUpdateByIdPort;
import by.grsu.skydiving.application.port.out.GetAcrobaticsOfAllMembersCompetitionPort;
import by.grsu.skydiving.application.port.out.GetRefereeingsPort;
import by.grsu.skydiving.application.port.out.GetTimeWithoutPenaltyPort;
import by.grsu.skydiving.application.port.out.GetTrickSerieShortInfoPort;
import by.grsu.skydiving.application.port.out.SaveTrickRefereeingPort;
import by.grsu.skydiving.application.port.out.UpdateTrickSeriePort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

@PersistenceAdapter
@RequiredArgsConstructor
public class TrickSerieForUpdatePersistenceAdapter implements SaveTrickRefereeingPort, GetRefereeingsPort,
    GetTrickSerieShortInfoPort, UpdateTrickSeriePort, GetTimeWithoutPenaltyPort,
    GetAcrobaticsOfAllMembersCompetitionPort, FindTrickSerieForUpdateByIdPort {
    private final TrickSerieJdbcRepository trickSerieJdbcRepository;
    private final CompetitionMemberDetailsJdbcRepository memberDetailsJdbcRepository;
    private final DSLContext dslContext;
    private final AcrobaticsShortInfoRecordMapper acrobaticsShortInfoRecordMapper;
    private final TrickSerieMapper mapper;

    @Override
    public TrickRefereeing saveAll(TrickRefereeingFullInfo fullInfo) {
        CompetitionMemberDetailsEntity competitionMemberDetailsEntity =
            memberDetailsJdbcRepository.findBySkydiverIdAndCompetitionId(fullInfo.skydiverId(),
                fullInfo.competitionId()); // TODO: check it later with new info from customer
        List<TrickSerieEntity> entitiesToSave = mapToEntities(fullInfo, competitionMemberDetailsEntity);
        List<TrickSerieEntity> savedEntities = trickSerieJdbcRepository.saveAll(entitiesToSave);

        List<TrickSerie> domains = mapper.toDomains(savedEntities);

        return mapper.toDomain(domains, fullInfo, competitionMemberDetailsEntity.getMemberNumber());
    }

    @Override
    public List<Refereeing> getCurrentRefeerings(Long refereeId) {
        List<RefereeingProjection> projections = trickSerieJdbcRepository.getRefereeingsByRefereeId(refereeId);

        return mapper.toRefereeingDomains(projections);
    }

    private List<TrickSerieEntity> mapToEntities(TrickRefereeingFullInfo fullInfo,
                                                 CompetitionMemberDetailsEntity memberDetails) {
        List<TrickSerieEntity> trickSerieEntities = fullInfo.referees().stream()
            .map(Referee::id)
            .map(id -> TrickSerieEntity.builder()
                    .refereeId(id)
                    .roundNumber(fullInfo.roundNumber())
                    .competitionMemberDetailId(memberDetails.getId())
                    .serieNumber(fullInfo.serieNumber())
                    .penaltyReason(PenaltyReason.NP.ordinal())
                    .isTimeSubmitted(null)
                .build())
            .toList();

        return trickSerieEntities;
    }

    @Override
    public TrickSerieShortInfo getTrickSerieShortInfoBeTrickSerieId(Long trickSerieId) {
        TrickSerieShortInfoProjection projection =
            trickSerieJdbcRepository.getTrickSerieShortInfoByTrickSerieId(trickSerieId);

        return mapper.toDomain(projection);
    }

    @Override
    public TrickSerieInfoForUpdate updateTrickSerie(TrickSerieInfoForUpdate trickSerieInfoForUpdate) {
        TrickSerieEntity entity = trickSerieJdbcRepository.findById(trickSerieInfoForUpdate.trickSerieId()).get();

        entity.setIsTimeSubmitted(trickSerieInfoForUpdate.isTimeSubmitted());
        entity.setTimeWithoutPenalty(trickSerieInfoForUpdate.timeWithoutPenalty());

        TrickSerieEntity updatedEntity = trickSerieJdbcRepository.save(entity);

        return mapper.toUpdatedSerieDomain(updatedEntity);
    }

    @Override
    public TrickSerieTime getTrickSerieTime(Long trickSerieId) {
        Float time = trickSerieJdbcRepository.getTimeWithoutPenalty(trickSerieId);

        return TrickSerieTime.builder()
            .timeWithoutPenalty(time)
            .trickSerieId(trickSerieId)
            .build();
    }

    @Override
    public List<AcrobaticsShortInfo> getAcrobaticsOfAllMembers(long competitionId) {
        return dslContext.select(
                TRICK_SERIE.COMPETITION_MEMBER_DETAIL_ID,
                multiset(
                    select(
                        TRICK_SERIE.ROUND_NUMBER,
                        avg(TRICK_SERIE.TIME_WITHOUT_PENALTY),
                        multiset(
                            select()
                                .from(TRICK_ATTEMPT)
                                .where(TRICK_ATTEMPT.TRICK_SERIE_ID.in(select(
                                        TRICK_SERIE.ID
                                    ).from(TRICK_SERIE)
                                        .where(TRICK_SERIE.ROUND_NUMBER.eq(TRICK_SERIE.ROUND_NUMBER)))
                                )
                        )
                    )
                        .from(TRICK_SERIE)
                        .where(TRICK_SERIE.COMPETITION_MEMBER_DETAIL_ID.eq(COMPETITION_MEMBER_DETAIL.ID))
                        .groupBy(TRICK_SERIE.ROUND_NUMBER)
                )
            )
            .from(COMPETITION)
            .leftJoin(COMPETITION_MEMBER_DETAIL)
            .on(COMPETITION_MEMBER_DETAIL.COMPETITION_ID.eq(COMPETITION.ID))
            .leftJoin(TRICK_SERIE)
            .on(TRICK_SERIE.COMPETITION_MEMBER_DETAIL_ID.eq(COMPETITION_MEMBER_DETAIL.ID))
            .where(COMPETITION.ID.eq(competitionId))
            .groupBy(TRICK_SERIE.COMPETITION_MEMBER_DETAIL_ID, COMPETITION_MEMBER_DETAIL.ID)
            .fetch()
            .stream().flatMap(acrobaticsShortInfoRecordMapper::toDomain)
            .toList();
    }

    @Override
    public Optional<TrickSerieInfoForUpdate> findById(long trickSerieId) {
        return trickSerieJdbcRepository.findById(trickSerieId)
            .map(entity -> TrickSerieInfoForUpdate.builder()
                .trickSerieId(entity.getId())
                .isTimeSubmitted(entity.getIsTimeSubmitted())
                .timeWithoutPenalty(entity.getTimeWithoutPenalty())
                .build());
    }
}
