package by.grsu.skydiving.application.domain.model.trick;

import java.util.List;
import java.util.Map;
import lombok.Builder;

@Builder
public record RefereeingResult(
        Long id,
        Long refereeId,
        Long refereeNumber,
        Float timeWithoutPenalty,
        Float totalPenalty,
        Float totalTime,
        Boolean isTimeSubmitted,
        PenaltyReason penaltyReason,
        Map<TrickType, TrickAttemptRefereeing> trickAttempts
){
    public static Float calculateTotalPenalty(PenaltyReason penaltyReason, List<TrickAttemptRefereeing> attempts) {
        if (penaltyReason != PenaltyReason.NP) {
            return 16f;
        }

        Float sum = attempts.stream()
            .map(TrickAttemptRefereeing::calculateTrickPenalty)
            .reduce(0f, Float::sum);

        return sum > 16 ? 16 : sum;
    }

    public boolean isCompleted() {
        return !trickAttempts.isEmpty();
    }
}
