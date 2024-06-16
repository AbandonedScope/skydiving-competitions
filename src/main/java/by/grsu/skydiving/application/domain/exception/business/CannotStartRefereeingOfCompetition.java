package by.grsu.skydiving.application.domain.exception.business;

public class CannotStartRefereeingOfCompetition extends BusinessException {
    public CannotStartRefereeingOfCompetition() {
        super("Нельзя начать судейство для соревнования, т.к. оно ещё не началось или уже закончилось.");
    }
}
