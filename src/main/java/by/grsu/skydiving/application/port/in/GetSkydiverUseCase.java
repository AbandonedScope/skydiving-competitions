package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;

public interface GetSkydiverUseCase {
    SkydiverShortInfo getById(long skydiverId);
}
