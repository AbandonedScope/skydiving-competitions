package by.grsu.skydiving.application.domain.model;

public record Weight(
        float measure,
        WeightUnits units
) {
    public Weight {
        if (measure < 0) {
            throw new IllegalArgumentException();
        }
    }
}
