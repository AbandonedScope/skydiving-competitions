package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trick.TrickRefereeing;
import by.grsu.skydiving.application.domain.model.trick.TrickRefereeingFullInfo;

public interface SaveTrickRefereeingPort {
    TrickRefereeing saveAll(TrickRefereeingFullInfo fullInfo);
}
