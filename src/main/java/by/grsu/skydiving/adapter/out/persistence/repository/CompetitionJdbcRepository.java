package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionEntity;
import java.util.List;
import java.util.Map;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionJdbcRepository extends CrudRepository<CompetitionEntity, Long> {
    List<CompetitionEntity> filter(Map<String, Object> filters, int pageSize, long offset);

    long countFiltered(Map<String, Object> filters);

    @Modifying
    @Query("""
        update competition
        set is_deleted = true
        where competition.id = :competitionId
        """)
    int softDeleteCompetitionById(Long competitionId);

    List<Long> updateCompetitionStatusesToRunning();

    List<Long> updateCompetitionStatusesToCompleted();

    List<Long> updateCompetitionStatusesToCanceled();
}

