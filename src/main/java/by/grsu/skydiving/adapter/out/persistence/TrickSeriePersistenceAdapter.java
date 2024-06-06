package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionMemberDetailsEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.TrickSerieEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.RefereeingProjection;
import by.grsu.skydiving.adapter.out.persistence.mapper.TrickSerieMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.CompetitionMemberDetailsJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.TrickSerieJdbcRepository;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.trickRefereeing.Refereeing;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeing;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeingFullInfo;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickSerie;
import by.grsu.skydiving.application.port.out.GetRefereeingsPort;
import by.grsu.skydiving.application.port.out.SaveTrickRefereeingPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TrickSeriePersistenceAdapter implements SaveTrickRefereeingPort, GetRefereeingsPort {
    private final TrickSerieJdbcRepository trickSerieJdbcRepository;
    private final CompetitionMemberDetailsJdbcRepository memberDetailsJdbcRepository;
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
        List<TrickSerieEntity> trickSerieEntities = new ArrayList<>();
        fullInfo.referees().stream()
            .map(Referee::id)
            .forEach(id -> trickSerieEntities.add(
                TrickSerieEntity.builder()
                    .refereeId(id)
                    .roundNumber(fullInfo.roundNumber())
                    .competitionMemberDetailId(memberDetails.getId())
                    .serieNumber(fullInfo.serieNumber())
                    .build()
            ));

        return trickSerieEntities;
    }
}
