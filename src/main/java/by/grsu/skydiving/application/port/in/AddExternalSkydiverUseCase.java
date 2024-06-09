package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;

public interface AddExternalSkydiverUseCase {
    Skydiver addExternalSkydiver(Skydiver skydiver);
}
