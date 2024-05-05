package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.Competition;

import java.util.Optional;

public interface FindCompetitionPort {
    Optional<Competition> findById(Long id);
}
