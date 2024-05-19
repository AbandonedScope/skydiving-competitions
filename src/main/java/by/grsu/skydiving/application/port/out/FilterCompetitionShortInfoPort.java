package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.competition.CompetitionShortInfo;
import java.util.Map;

public interface FilterCompetitionShortInfoPort {
    DomainPage<CompetitionShortInfo> filter(Map<String, Object> filters, long pageNumber, int pageSize);
}
