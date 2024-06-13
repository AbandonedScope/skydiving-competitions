package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;

public interface SaveCompetitionCollegiumPort {
    CompetitionCollegium saveCollegium(Competition competition);
}
