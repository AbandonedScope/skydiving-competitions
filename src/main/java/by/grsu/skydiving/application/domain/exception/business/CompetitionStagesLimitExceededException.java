package by.grsu.skydiving.application.domain.exception.business;

public class CompetitionStagesLimitExceededException extends BusinessException {
    public CompetitionStagesLimitExceededException(int limit, int stageNumber) {
        super("Number of stages for competition was set to '%d', but tried to crete '%d'-th collegium."
            .formatted(limit, stageNumber));
    }
}
