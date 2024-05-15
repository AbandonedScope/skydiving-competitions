package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionMemberDetailsEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface CompetitionMemberDetailsJdbcRepository extends ListCrudRepository<CompetitionMemberDetailsEntity, Long> {
}
