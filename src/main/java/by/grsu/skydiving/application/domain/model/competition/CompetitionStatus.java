package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.exception.domain.InvalidCompetitionStatusNumberException;
import by.grsu.skydiving.application.domain.model.common.AdjacencyMatrix;
import by.grsu.skydiving.application.domain.model.common.EntityStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CompetitionStatus implements EntityStatus<CompetitionStatus> {
    INITIAL(1, "Competition was just created, some fields con de not initialized."),
    CREATED(2, "Competition is completely initialized."),
    RUNNING(3, "Competition is currently running."),
    COMPLETED(4, "Competition ended.");

    private static final AdjacencyMatrix<CompetitionStatus> ADJACENCY_MATRIX;

    static {
        ADJACENCY_MATRIX = AdjacencyMatrix.<CompetitionStatus>builder()
                .build();
    }

    private final int number;
    private final String description;

    public static CompetitionStatus of(int number) {
        return Arrays.stream(CompetitionStatus.values())
                .filter(status -> status.number == number)
                .findFirst()
                .orElseThrow(() -> new InvalidCompetitionStatusNumberException(number));
    }

    @Override
    public AdjacencyMatrix<CompetitionStatus> getAdjacencyMatrix() {
        return ADJACENCY_MATRIX;
    }

    @Override
    public CompetitionStatus getThis() {
        return this;
    }
}
