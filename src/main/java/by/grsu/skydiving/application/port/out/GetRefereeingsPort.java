package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trick.Refereeing;
import java.util.List;

public interface GetRefereeingsPort {
    List<Refereeing> getCurrentRefeerings(Long refereeId);
}
