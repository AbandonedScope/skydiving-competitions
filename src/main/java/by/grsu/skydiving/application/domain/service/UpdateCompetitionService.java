package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.skydiver.Address;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.application.port.out.UpdateCompetitionUseCase;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateCompetitionService implements UpdateCompetitionUseCase {
    private final GetUpdatableCompetitionUseCase getCompetitionUseCase;
    private final SaveCompetitionPort saveCompetitionPort;

    @Override
    public Competition updateCompetition(UpdateCompetitionCommand command) {
        Competition competition = getCompetitionUseCase.getCompetitionThatCanBeUpdated(command.id());

        updateCompetition(competition, command);
        competition = saveCompetitionPort.saveInitial(competition);
        return competition;
    }

    void updateCompetition(Competition competition, UpdateCompetitionCommand command) {
        competition.setName(command.name());
        competition.setBeginDate(command.beginDate());
        competition.setEndDate(command.endDate());
        competition.setPlace(new Address(command.place()));
    }
}
