package by.grsu.skydiving.application.domain.exception.business;

public class CompetitionCanNotBeRefereedException
    extends BusinessException {
    public CompetitionCanNotBeRefereedException() {
        super("Невозможно занести данные о судействе соревнования, которое ещё не началось или уже закончилось.");
    }
}
