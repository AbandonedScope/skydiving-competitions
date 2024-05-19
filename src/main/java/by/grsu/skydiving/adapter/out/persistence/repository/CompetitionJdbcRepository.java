package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionEntity;
import java.util.List;
import java.util.Map;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionJdbcRepository extends CrudRepository<CompetitionEntity, Long> {
    List<CompetitionEntity> filter(Map<String, Object> filters, int pageSize, long offset);

    long countFiltered(Map<String, Object> filters);
}

