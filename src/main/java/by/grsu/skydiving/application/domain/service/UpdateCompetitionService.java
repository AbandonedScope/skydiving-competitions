package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.TryToUpdateImmutableCompetitionException;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.skydiver.Address;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.application.port.out.UpdateCompetitionUseCase;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateCompetitionService implements UpdateCompetitionUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;
    private final SaveCompetitionPort saveCompetitionPort;

    @Override
    public Competition updateCompetition(UpdateCompetitionCommand command) {
        Competition competition = getCompetitionUseCase.getCompetition(command.id());

        if (!competition.canBeUpdated()) {
            throw new TryToUpdateImmutableCompetitionException("Competition can't be updated");
        }

        updateCompetition(competition, command);
        competition = saveCompetitionPort.save(competition);
        return competition;
    }

    void updateCompetition(Competition competition, UpdateCompetitionCommand command) {
        competition.setName(command.name());
        competition.setBeginDate(command.beginDate());
        competition.setEndDate(command.endDate());
        competition.setPlace(new Address(command.place()));
        competition.setNumberOfStages(command.numberOfStages());
    }
}
