package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.competition.CompetitionShortInfo;

public interface GetCompetitionPageUseCase {
    DomainPage<CompetitionShortInfo> getPage(GetPageQuery<CompetitionFilterQuery> query);

    record CompetitionFilterQuery(
        Boolean isActive
    ) {
    }
}
