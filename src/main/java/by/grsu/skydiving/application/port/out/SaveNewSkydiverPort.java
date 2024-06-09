package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;

public interface SaveNewSkydiverPort {
    Skydiver save(Skydiver skydiver);

    Skydiver saveExternal(Skydiver skydiver);
}
