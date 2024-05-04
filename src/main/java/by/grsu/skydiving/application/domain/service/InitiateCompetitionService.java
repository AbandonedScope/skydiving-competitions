package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStatus;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class InitiateCompetitionService implements InitiateCompetitionUseCase {
    @Override
    public Competition initiateCompetition(InitiateCompetitionCommand command) {
        Competition competition = buildCompetition(command);


        return null;
    }

    private Competition buildCompetition(InitiateCompetitionCommand command) {
        return Competition.builder()
                .status(CompetitionStatus.INITIAL)
                .name(command.name())
                .beginDate(command.beginDate())
                .endDate(command.endDate())
                .place(command.placeOfCompetition())
                .build();
    }
}
