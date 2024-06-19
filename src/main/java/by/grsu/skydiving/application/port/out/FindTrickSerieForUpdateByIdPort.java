package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;
import java.util.Optional;

public interface FindTrickSerieForUpdateByIdPort {
    Optional<TrickSerieInfoForUpdate> findById(long trickSerieId);
}
