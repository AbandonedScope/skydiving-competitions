package by.grsu.skydiving.application.port.out;


import by.grsu.skydiving.application.domain.model.Competition;

public interface SaveCompetitionPort {
    Competition save(Competition competition);
}
