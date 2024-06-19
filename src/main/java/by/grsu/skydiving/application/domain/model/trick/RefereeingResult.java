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
        Boolean isTimeSubmitted,
        Float totalPenalty,
        PenaltyReason penaltyReason,
        Map<TrickType, TrickAttempt> trickAttempts
){
    public static Float calculateTotalPenalty(PenaltyReason penaltyReason, List<TrickAttempt> attempts) {
        if (penaltyReason != PenaltyReason.NP) {
            return 16f;
        }

        Float sum = attempts.stream()
            .map(TrickAttempt::calculateTrickPenalty)
            .reduce(0f, Float::sum);

        return sum > 16 ? 16 : sum;
    }
}
