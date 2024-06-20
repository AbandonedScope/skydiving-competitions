package by.grsu.skydiving.application.domain.model.trick;

import lombok.Builder;

@Builder
public record TrickAttemptForUpdate(
        Long id,
        Integer arrowPenalty,
        Integer dPenalty,
        Integer sPenalty,
        Integer minusPenalty,
        Integer plusMinusPenalty
) {
}
