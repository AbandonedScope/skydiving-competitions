package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;

public interface GetSkydiverPagePort {
    DomainPage<SkydiverShortInfo> getPage(long pageNumber, int pageSize);
}
