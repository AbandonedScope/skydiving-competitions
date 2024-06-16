package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;

public interface UpdateExternalSkydiverUseCase {
    Skydiver updateExternal(Long skydiverId, Skydiver skydiver);
}
