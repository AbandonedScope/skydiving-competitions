package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.CollegiumRefereeTransEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionCollegiumEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.CompetitionStageEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.CollegiumRefereeTransJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.CompetitionCollegiumJdbcRepository;
import by.grsu.skydiving.application.domain.model.RefereeCollegium;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import by.grsu.skydiving.application.port.out.FindCollegiumOfCompetitionPort;
import by.grsu.skydiving.application.port.out.FindRefereesPort;
import by.grsu.skydiving.application.port.out.SaveCompetitionCollegiumPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionCollegiumPersistenceAdapter implements
    SaveCompetitionCollegiumPort, FindCollegiumOfCompetitionPort {
    private final CompetitionCollegiumJdbcRepository stageRepository;
    private final CollegiumRefereeTransJdbcRepository transRepository;
    private final FindRefereesPort findRefereesPort;
    private final CompetitionStageEntityMapper mapper;

    @Override
    public CompetitionCollegium saveCollegium(Competition competition) {
        CompetitionCollegium collegium = competition.getCollegium();
        CompetitionCollegiumEntity collegiumEntity = mapper.toEntity(collegium, competition.getId());
        collegiumEntity = stageRepository.save(collegiumEntity);

        List<CollegiumRefereeTransEntity> trans = extractCollegiumRefereeTrans(collegium);
        saveCollegiumRefereeTrans(trans, collegiumEntity.getId());

        return mapper.toDomain(collegiumEntity, collegium.mainCollegium(), collegium.collegium());
    }

    @Override
    public Optional<CompetitionCollegium> findByCompetitionId(long competitionId) {
        return stageRepository.findByCompetitionId(competitionId)
            .map(mapper::toDomain)
            .map(this::enrichWithReferees);
    }

    private CompetitionCollegium enrichWithReferees(CompetitionCollegium stage) {
        Optional<RefereeGroups> optionalRefereeGroups = findRefereesPort.findRefereesByCompetitionStageId(stage.id());
        if (optionalRefereeGroups.isPresent()) {
            RefereeGroups groups = optionalRefereeGroups.get();

            RefereeCollegium mainCollegium = new RefereeCollegium(groups.mainCollegium());
            RefereeCollegium collegium = new RefereeCollegium(groups.collegium());

            stage = stage.withMainCollegium(mainCollegium)
                .withCollegium(collegium);
        }

        return stage;
    }

    private List<CollegiumRefereeTransEntity> extractCollegiumRefereeTrans(CompetitionCollegium collegium) {
        return Stream.concat(extractMainCollegium(collegium), extractCollegium(collegium))
            .toList();
    }

    private Stream<CollegiumRefereeTransEntity> extractMainCollegium(CompetitionCollegium stage) {
        return stage.mainCollegium().collegium().stream()
            .map(collegiumReferee -> mapper.toEntity(collegiumReferee, stage.id(), true));
    }

    private Stream<CollegiumRefereeTransEntity> extractCollegium(CompetitionCollegium stage) {
        return stage.collegium().collegium().stream()
            .map(collegiumReferee -> mapper.toEntity(collegiumReferee, stage.id(), false));
    }

    private void saveCollegiumRefereeTrans(List<CollegiumRefereeTransEntity> trans, long competitionCollegiumId) {
        transRepository.deleteAllByCompetitionCollegiumId(competitionCollegiumId);
        transRepository.saveAll(trans);
    }
}
