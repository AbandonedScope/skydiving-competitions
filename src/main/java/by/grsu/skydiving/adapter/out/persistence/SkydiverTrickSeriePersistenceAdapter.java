package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickAttemptEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.TrickSerieProjection;
import by.grsu.skydiving.adapter.out.persistence.mapper.TrickAttemptEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.mapper.TrickSerieMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.TrickAttemptJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.TrickSerieJdbcRepository;
import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trick.TrickAttempt;
import by.grsu.skydiving.application.domain.model.trick.TrickAttemptsWithScore;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieExtended;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieOfSkydiver;
import by.grsu.skydiving.application.domain.model.trick.TrickType;
import by.grsu.skydiving.application.port.out.GetTrickSeriesByCompetitionIdPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SkydiverTrickSeriePersistenceAdapter implements GetTrickSeriesByCompetitionIdPort {
    private final TrickSerieJdbcRepository trickSerieJdbcRepository;
    private final TrickAttemptJdbcRepository trickAttemptJdbcRepository;
    private final TrickAttemptEntityMapper mapper;
    private final TrickSerieMapper serieMapper;

    @Override
    public List<TrickSerieOfSkydiver> getByCompetitionId(Long competitionId) {
        List<TrickSerieProjection> trickSeries = trickSerieJdbcRepository.getTrickSeriesByCompetitionId(competitionId);
        List<TrickAttemptEntity> trickAttempts = trickAttemptJdbcRepository.getTrickAttemptByCompetitionId(competitionId);

        List<TrickSerieOfSkydiver> trickSerieOfSkydivers = new ArrayList<>();
        trickSeries.forEach(trickSerieProjection -> trickSerieOfSkydivers.add(
            mapToDomainModel(trickSerieProjection, filterByTrickSerieId(trickSerieProjection.getId(), trickAttempts))));

        return trickSerieOfSkydivers;
    }

    private List<TrickAttemptEntity> filterByTrickSerieId(Long trickSerieId, List<TrickAttemptEntity> attempts){
        return attempts.stream()
                .filter(x -> x.getTrickSerieId().equals(trickSerieId))
                .toList();
    }

    private TrickSerieOfSkydiver mapToDomainModel(TrickSerieProjection trickSerie, List<TrickAttemptEntity> trickAttempts){
        TrickAttemptsWithScore trickAttemptsWithScore =
            mapToTrickAttempts(trickAttempts, PenaltyReason.of(trickSerie.getPenaltyReason()));
        TrickSerieExtended extended = serieMapper.mapToExtended(trickSerie, trickAttemptsWithScore);

        return serieMapper.mapToTrickSerieOfSkydiver(trickSerie, extended);
    }

    private TrickAttemptsWithScore mapToTrickAttempts(List<TrickAttemptEntity> entities, PenaltyReason reason){
        Map<TrickType, TrickAttempt> tricksMap = HashMap.newHashMap(6);
        List<TrickAttempt> attempts = entities.stream()
                .map(mapper::mapToDomain)
                .toList();
        attempts.forEach(x -> tricksMap.put(x.trickType(), x));

        return TrickAttemptsWithScore.builder()
                .trickAttempts(tricksMap)
                .penaltyReason(reason)
                .totalScore(TrickAttemptsWithScore.calculateTotalPenalty(reason, attempts))
                .build();
    }
}
