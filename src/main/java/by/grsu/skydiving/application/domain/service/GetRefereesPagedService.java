package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.PageNumberInvalidException;
import by.grsu.skydiving.application.domain.exception.business.PageSizeInvalidException;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.port.in.GetFilteredRefereesUseCase;
import by.grsu.skydiving.application.port.out.FilterRefereesPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class GetRefereesPagedService implements GetFilteredRefereesUseCase {
    private final FilterRefereesPort filterRefereesPort;

    @Override
    public DomainPage<Referee> getPage(GetPageQuery query) {
        long pageNumber = query.pageNumber();
        int pageSize = query.pageSize();

        if (query.pageNumber() <= 0) {
            throw new PageNumberInvalidException(pageNumber);
        }

        if (query.pageSize() <= 0) {
            throw new PageSizeInvalidException(pageSize);
        }
        --pageNumber;

        Map<String, Object> filters = query.filterQuery().filters();
        return filterRefereesPort.filter(filters, pageNumber, pageSize);
    }
}
