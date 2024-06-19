package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trick.Refereeing;
import java.util.List;

public interface GetRefereeingsUseCase {
    List<Refereeing> getCurrentRefereeing(Long refereeId);
}
