package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.CompetitionEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.CompetitionJdbcRepository;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionShortInfo;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStage;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.out.ExistsCompetitionPort;
import by.grsu.skydiving.application.port.out.FilterCompetitionShortInfoPort;
import by.grsu.skydiving.application.port.out.FindCompetitionPort;
import by.grsu.skydiving.application.port.out.GetMembersOfCompetitionPort;
import by.grsu.skydiving.application.port.out.GetStagesOfCompetitionPort;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.application.port.out.SaveCompetitionStagesPort;
import by.grsu.skydiving.application.port.out.SaveCompetitionTeamsPort;
import by.grsu.skydiving.application.port.out.SaveIndividualsPort;
import by.grsu.skydiving.application.port.out.SoftDeleteCompetitionPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionPersistenceAdapter implements SaveCompetitionPort,
    FindCompetitionPort, FilterCompetitionShortInfoPort,
    SoftDeleteCompetitionPort, ExistsCompetitionPort {
    private final CompetitionJdbcRepository competitionRepository;
    private final SaveCompetitionStagesPort saveStagesPort;
    private final GetStagesOfCompetitionPort getStagesOfCompetitionPort;
    private final SaveCompetitionTeamsPort teamPort;
    private final SaveIndividualsPort saveIndividualsPort;
    private final GetMembersOfCompetitionPort getMembersOfCompetitionPort;
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
        List<CompetitionStage> stages = saveStagesPort.saveStages(competition);
        List<Team> teams = teamPort.saveTeams(competition);
        saveIndividualsPort.saveIndividuals(competition.getIndividuals(), competition.getId());

        competition = mapper.toDomain(entity);
        competition.setStages(stages);
        competition.setTeams(teams);


        return competition;
    }

    @Override
    public boolean existsById(long competitionId) {
        return competitionRepository.existsById(competitionId);
    }

    @Override
    public Optional<Competition> findFullById(Long id) {
        return competitionRepository.findById(id)
            .map(mapper::toDomain)
            .map(this::enrichWithMembers)
            .map(this::enrichWithStages);
    }

    @Override
    public DomainPage<CompetitionShortInfo> filter(Map<String, Object> filters, long pageNumber, int pageSize) {
        formatFilters(filters);
        long offset = pageNumber * pageSize;

        List<CompetitionEntity> list = competitionRepository.filter(new HashMap<>(filters), pageSize, offset);
        List<CompetitionShortInfo> competitionShortInfos = mapper.toDomainShortInfos(list);
        long totalRows = competitionRepository.countFiltered(new HashMap<>(filters));

        int totalPages = (int) totalRows / pageSize;
        if (totalRows % pageSize > 0) {
            totalPages++;
        }

        return DomainPage.<CompetitionShortInfo>builder()
            .pageSize(pageSize)
            .currentPage(++pageNumber)
            .totalPages(totalPages)
            .content(competitionShortInfos)
            .build();
    }

    @Override
    public void deleteCompetition(Long competitionId) {
        competitionRepository.softDeleteCompetitionById(competitionId);
    }

    private Competition enrichWithMembers(Competition competition) {
        var members = getMembersOfCompetitionPort.getByCompetitionId(competition.getId());
        var teams = members.teams();
        var individuals = members.individuals();

        competition.setTeams(teams);
        competition.setIndividuals(individuals);

        return competition;
    }

    private Competition enrichWithStages(Competition competition) {
        var stages = getStagesOfCompetitionPort.getByCompetitionId(competition.getId());
        competition.setStages(stages);

        return competition;
    }

    private void formatFilters(Map<String, Object> filters) {
        Boolean isCompleted = (Boolean) filters.get("isCompleted");
        if (isCompleted != null) {
            filters.put("isCompleted", isCompleted);
        }
    }
}
