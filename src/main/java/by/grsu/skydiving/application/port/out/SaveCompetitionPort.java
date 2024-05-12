package by.grsu.skydiving.application.port.out;


import by.grsu.skydiving.application.domain.model.competition.Competition;

public interface SaveCompetitionPort {
    Competition saveInitial(Competition competition);

    Competition save(Competition competition);
}
