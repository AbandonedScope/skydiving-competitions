package by.grsu.skydiving.application.domain.service;

import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.NAME_FILTER;

import by.grsu.skydiving.application.domain.exception.business.PageNumberInvalidException;
import by.grsu.skydiving.application.domain.exception.business.PageSizeInvalidException;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.port.in.GetSkydiversPageUseCase;
import by.grsu.skydiving.application.port.out.FilterSkydiversShortInfoPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetSkydiversPageService implements GetSkydiversPageUseCase {
    private final FilterSkydiversShortInfoPort filterPort;

    @Override
    public DomainPage<SkydiverShortInfo> getPage(GetPageQuery query) {
        long pageNumber = query.pageNumber();
        int pageSize = query.pageSize();

        if (query.pageNumber() <= 0) {
            throw new PageNumberInvalidException(pageNumber);
        }

        if (query.pageSize() <= 0) {
            throw new PageSizeInvalidException(pageSize);
        }
        --pageNumber;

        var filters = query.filterQuery().filters();
        String nameFilter = (String) filters.get(NAME_FILTER);
        if (nameFilter != null
            && nameFilter.isBlank()) {
            filters.remove(NAME_FILTER);
        }

        return filterPort.filter(filters, pageNumber, pageSize);
    }
}
