package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import java.util.Optional;

public interface FindSkydiverByIdPort {
    Optional<SkydiverShortInfo> findById(long skydiverId);
}
