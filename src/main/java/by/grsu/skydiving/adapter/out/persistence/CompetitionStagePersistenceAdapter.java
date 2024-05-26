package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionStageEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.StageRefereeTransEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.CompetitionStageEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.CompetitionStageJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.StageRefereeTransJdbcRepository;
import by.grsu.skydiving.application.domain.model.RefereeCollegium;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStage;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import by.grsu.skydiving.application.port.out.FindRefereesPort;
import by.grsu.skydiving.application.port.out.GetStagesOfCompetitionPort;
import by.grsu.skydiving.application.port.out.SaveCompetitionStagesPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionStagePersistenceAdapter implements
    SaveCompetitionStagesPort, GetStagesOfCompetitionPort {
    private final CompetitionStageJdbcRepository stageRepository;
    private final StageRefereeTransJdbcRepository transRepository;
    private final FindRefereesPort findRefereesPort;
    private final CompetitionStageEntityMapper mapper;

    @Override
    public List<CompetitionStage> saveStages(Competition competition) {
        List<CompetitionStage> stages = competition.getStages();
        List<CompetitionStageEntity> stageEntities = mapper.toEntities(stages, competition.getId());
        stageEntities = stageRepository.saveAll(stageEntities);
        List<CompetitionStage> savedStages = new ArrayList<>();
        for (int i = 0; i < stages.size(); i++) {
            CompetitionStage stage = stages.get(i);
            CompetitionStage savedStage =
                mapper.toDomain(stageEntities.get(i), stage.mainCollegium(), stage.collegium());
            savedStages.add(savedStage);
        }

        List<StageRefereeTransEntity> trans = extractStageRefereeTrans(savedStages);
        saveStageRefereeTrans(trans);

        return savedStages;
    }

    @Override
    public List<CompetitionStage> getByCompetitionId(long competitionId) {
        return stageRepository.findByCompetitionId(competitionId).stream()
            .map(mapper::toDomain)
            .map(this::enrichWithCollegiums)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private CompetitionStage enrichWithCollegiums(CompetitionStage stage) {
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

    private List<StageRefereeTransEntity> extractStageRefereeTrans(List<CompetitionStage> stages) {
        return stages.stream()
            .flatMap(stage -> Stream.concat(extractMainCollegium(stage), extractCollegium(stage)))
            .toList();
    }

    private Stream<StageRefereeTransEntity> extractMainCollegium(CompetitionStage stage) {
        return stage.mainCollegium().collegium().stream()
            .map(collegiumReferee -> mapper.toEntity(collegiumReferee, stage.id(), true));
    }

    private Stream<StageRefereeTransEntity> extractCollegium(CompetitionStage stage) {
        return stage.collegium().collegium().stream()
            .map(collegiumReferee -> mapper.toEntity(collegiumReferee, stage.id(), false));
    }

    private void saveStageRefereeTrans(List<StageRefereeTransEntity> trans) {
        transRepository.saveAll(trans);
    }
}
