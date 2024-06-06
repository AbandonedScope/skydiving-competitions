package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import java.util.Map;

public interface FilterSkydiversShortInfoPort {
    DomainPage<SkydiverShortInfo> filter(Map<String, Object> filters, long pageNumber, int pageSize);
}
