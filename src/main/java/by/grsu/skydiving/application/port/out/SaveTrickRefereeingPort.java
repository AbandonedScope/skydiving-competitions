package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeing;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeingFullInfo;

public interface SaveTrickRefereeingPort {
    TrickRefereeing saveAll(TrickRefereeingFullInfo fullInfo);
}
