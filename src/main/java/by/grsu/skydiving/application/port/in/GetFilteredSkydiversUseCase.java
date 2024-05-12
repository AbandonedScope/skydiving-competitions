package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.common.FilterQuery;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;

import java.util.List;

public interface GetFilteredSkydiversUseCase {
    List<SkydiverShortInfo> getFiltered(FilterQuery query);
}
