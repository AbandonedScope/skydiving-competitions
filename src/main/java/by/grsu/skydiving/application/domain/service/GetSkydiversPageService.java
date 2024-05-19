package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.PageNumberInvalidException;
import by.grsu.skydiving.application.domain.exception.business.PageSizeInvalidException;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.common.SkydiverFilterQuery;
import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.domain.model.skydiver.SportDegree;
import by.grsu.skydiving.application.port.in.GetSkydiversPageUseCase;
import by.grsu.skydiving.application.port.out.FilterSkydiversShortInfoPort;
import by.grsu.skydiving.common.UseCase;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetSkydiversPageService implements GetSkydiversPageUseCase {
    private final FilterSkydiversShortInfoPort filterPort;

    @Override
    public DomainPage<SkydiverShortInfo> getPage(GetPageQuery<SkydiverFilterQuery> query) {
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

    private Map<String, Object> buildFilters(SkydiverFilterQuery query) {
        Map<String, Object> filters = HashMap.newHashMap(6);

        Gender gender = query.gender();
        if (gender != null) {
            filters.put("gender", gender);
        }

        String name = query.name();
        if (name != null) {
            filters.put("name", name);
        }

        Boolean isInternal = query.isInternal();
        if (isInternal != null) {
            filters.put("isInternal", isInternal);
        }

        SportDegree sportDegree = query.sportDegree();
        if (sportDegree != null) {
            filters.put("sportDegree", sportDegree);
        }

        return filters;
    }
}
