package by.grsu.skydiving.application.domain.model.parachut;

import by.grsu.skydiving.application.domain.model.skydiver.Weight;

public record ParachutTechnicalCharacteristics(
        Short domeArea,
        Short layingVolume,
        Weight maxFlightWeight,
        Weight ParachutWeight
) {
}
