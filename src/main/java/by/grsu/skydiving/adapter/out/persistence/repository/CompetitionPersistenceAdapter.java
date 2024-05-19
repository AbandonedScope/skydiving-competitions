package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionStageEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.StageRefereeTransEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.CompetitionEntityMapper;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionShortInfo;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStage;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStatus;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.out.FilterCompetitionShortInfoPort;
import by.grsu.skydiving.application.port.out.FindCompetitionPort;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.application.port.out.SaveCompetitionTeamsPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionPersistenceAdapter implements SaveCompetitionPort,
    FindCompetitionPort, FilterCompetitionShortInfoPort {
    private final CompetitionJdbcRepository competitionRepository;
    private final CompetitionStageJdbcRepository stageRepository;
    private final StageRefereeTransJdbcRepository transRepository;
    private final SaveCompetitionTeamsPort teamPort;
    private final CompetitionEntityMapper mapper;

    @Override
    public Competition saveInitial(Competition competition) {
        CompetitionEntity entity = mapper.toEntity(competition);
        entity = competitionRepository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    @Transactional
    public Competition save(Competition competition) {
        CompetitionEntity entity = mapper.toEntity(competition);
        entity = competitionRepository.save(entity);
        List<CompetitionStage> stages = saveStages(competition);
        List<Team> teams = teamPort.saveTeams(competition);

        competition = mapper.toDomain(entity);
        competition.setStages(stages);
        competition.setTeams(teams);

        return competition;
    }

    @Override
    public Optional<Competition> findById(Long id) {
        return competitionRepository.findById(id)
            .map(this::mapToDomain);
    }

    @Override
    public DomainPage<CompetitionShortInfo> filter(Map<String, Object> filters, long pageNumber, int pageSize) {
        formatFilters(filters);
        long offset = pageNumber * pageSize;

        List<CompetitionEntity> list = competitionRepository.filter(new HashMap<>(filters), pageSize, offset);
        List<CompetitionShortInfo> skydiver = mapper.toDomainShortInfos(list);
        long totalRows = competitionRepository.countFiltered(new HashMap<>(filters));

        int totalPages = (int) totalRows / pageSize;
        if (totalRows % pageSize > 0) {
            totalPages++;
        }

        return DomainPage.<CompetitionShortInfo>builder()
            .pageSize(pageSize)
            .currentPage(++pageNumber)
            .totalPages(totalPages)
            .content(skydiver)
            .build();
    }

    private List<CompetitionStage> saveStages(Competition competition) {
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

    private Competition mapToDomain(CompetitionEntity entity) {
        List<CompetitionStageEntity> stages = stageRepository.findByCompetitionId(entity.getId());

        return mapper.toDomain(entity, stages);
    }

    void formatFilters(Map<String, Object> filters) {
        Boolean isActive = (Boolean) filters.get("isActive");
        if (isActive != null && isActive) {
            filters.put("status", CompetitionStatus.RUNNING.getNumber());
        }
    }
}
