package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;

public interface GetSkydiversPageUseCase {
    DomainPage<SkydiverShortInfo> getPage(GetPageQuery query);
}
