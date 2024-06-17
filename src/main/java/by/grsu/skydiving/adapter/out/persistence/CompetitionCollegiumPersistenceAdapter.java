package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.CollegiumRefereeTransEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionCollegiumEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.CompetitionCollegiumEntityMapper;
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
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionCollegiumPersistenceAdapter implements
    SaveCompetitionCollegiumPort, FindCollegiumOfCompetitionPort {
    private final CompetitionCollegiumJdbcRepository collegiumRepository;
    private final CollegiumRefereeTransJdbcRepository transRepository;
    private final FindRefereesPort findRefereesPort;
    private final CompetitionCollegiumEntityMapper mapper;

    @Override
    @Transactional
    public CompetitionCollegium saveCollegium(Competition competition) {
        CompetitionCollegium collegium = competition.getCollegium();
        if (collegium == null) {
            collegiumRepository.deleteByCompetitionId(competition.getId());
            return null;
        }

        CompetitionCollegiumEntity collegiumEntity = mapper.toEntity(collegium, competition.getId());
        collegiumEntity = collegiumRepository.save(collegiumEntity);

        collegium = collegium.withId(collegiumEntity.getId());
        List<CollegiumRefereeTransEntity> trans = extractCollegiumRefereeTrans(collegium);
        saveCollegiumRefereeTrans(trans, collegiumEntity.getId());

        return mapper.toDomain(collegiumEntity, collegium.mainCollegium(), collegium.collegium());
    }

    @Override
    public Optional<CompetitionCollegium> findByCompetitionId(long competitionId) {
        return collegiumRepository.findByCompetitionId(competitionId)
            .map(mapper::toDomain)
            .map(this::enrichWithReferees);
    }

    private CompetitionCollegium enrichWithReferees(CompetitionCollegium collegium) {
        Optional<RefereeGroups> optionalRefereeGroups =
            findRefereesPort.findRefereesByCompetitionCollegiumId(collegium.id());
        if (optionalRefereeGroups.isPresent()) {
            RefereeGroups groups = optionalRefereeGroups.get();

            RefereeCollegium mainCollegium = new RefereeCollegium(groups.mainCollegium());
            RefereeCollegium commonCollegium = new RefereeCollegium(groups.collegium());

            collegium = collegium.withMainCollegium(mainCollegium)
                .withCollegium(commonCollegium);
        }

        return collegium;
    }

    private List<CollegiumRefereeTransEntity> extractCollegiumRefereeTrans(CompetitionCollegium collegium) {
        return Stream.concat(extractMainCollegium(collegium), extractCollegium(collegium))
            .toList();
    }

    private Stream<CollegiumRefereeTransEntity> extractMainCollegium(CompetitionCollegium collegium) {
        return collegium.mainCollegium().collegium().stream()
            .map(collegiumReferee -> mapper.toEntity(collegiumReferee, collegium.id(), true));
    }

    private Stream<CollegiumRefereeTransEntity> extractCollegium(CompetitionCollegium collegium) {
        return collegium.collegium().collegium().stream()
            .map(collegiumReferee -> mapper.toEntity(collegiumReferee, collegium.id(), false));
    }

    private void saveCollegiumRefereeTrans(List<CollegiumRefereeTransEntity> trans, long competitionCollegiumId) {
        transRepository.deleteAllByCompetitionCollegiumId(competitionCollegiumId);
        transRepository.saveAll(trans);
    }
}
