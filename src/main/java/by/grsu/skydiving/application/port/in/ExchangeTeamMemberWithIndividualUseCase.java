package by.grsu.skydiving.application.port.in;

public interface ExchangeTeamMemberWithIndividualUseCase {
    void exchange(ExchangeTeamMemberWithIndividualCommand command);

    record ExchangeTeamMemberWithIndividualCommand(
        long competitionId,
        long teamId,
        long teamMemberId,
        long individualId
    ) {
    }
}
