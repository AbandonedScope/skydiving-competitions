package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.PageNumberInvalidException;
import by.grsu.skydiving.application.domain.exception.business.PageSizeInvalidException;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.competition.CompetitionShortInfo;
import by.grsu.skydiving.application.port.in.GetCompetitionPageUseCase;
import by.grsu.skydiving.application.port.out.FilterCompetitionShortInfoPort;
import by.grsu.skydiving.common.UseCase;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetCompetitionPageService implements GetCompetitionPageUseCase {
    private final FilterCompetitionShortInfoPort filterPort;

    @Override
    public DomainPage<CompetitionShortInfo> getPage(GetPageQuery<CompetitionFilterQuery> query) {
        long pageNumber = query.pageNumber();
        int pageSize = query.pageSize();

        if (query.pageNumber() <= 0) {
            throw new PageNumberInvalidException(pageNumber);
        }

        if (query.pageSize() <= 0) {
            throw new PageSizeInvalidException(pageSize);
        }
        --pageNumber;

        Map<String, Object> filters = buildFilters(query.filterQuery());
        return filterPort.filter(filters, pageNumber, pageSize);
    }

    private Map<String, Object> buildFilters(CompetitionFilterQuery query) {
        Map<String, Object> filters = HashMap.newHashMap(1);

        Boolean isCompleted = query.isCompleted();
        if (isCompleted != null) {
            filters.put("isCompleted", isCompleted);
        }

        return filters;
    }
}
