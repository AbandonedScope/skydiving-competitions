package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.Referee;

public interface SaveRefereePort {
    Long save(Referee referee);
}
