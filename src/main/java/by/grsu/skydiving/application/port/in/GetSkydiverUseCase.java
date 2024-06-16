package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;

public interface GetSkydiverUseCase {
    SkydiverShortInfo getByIdShort(long skydiverId);

    Skydiver getById(long skydiverId);
}
