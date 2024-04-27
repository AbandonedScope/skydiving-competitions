package by.grsu.skydiving.application.domain.model;

public record ParachutTechnicalCharacteristics(
        Short domeArea,
        Short layingVolume,
        Weight maxFlightWeight,
        Weight ParachutWeight
) {
}
