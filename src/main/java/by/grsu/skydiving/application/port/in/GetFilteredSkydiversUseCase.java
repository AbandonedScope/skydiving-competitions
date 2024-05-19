package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.common.SkydiverFilterQuery;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import java.util.List;

public interface GetFilteredSkydiversUseCase {
    List<SkydiverShortInfo> getFiltered(SkydiverFilterQuery query);
}
