package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.exception.domain.InvalidCompetitionStatusIdException;
import by.grsu.skydiving.application.domain.model.common.AdjacencyMatrix;
import by.grsu.skydiving.application.domain.model.common.EntityStatus;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompetitionStatus implements EntityStatus<CompetitionStatus> {
    INITIAL(1, "Соревнование создано и содержит основную информацию, но команды и/или судьи ещё не добавлены."),
    CREATED(2, "Соревнование полностью инициализировано."),
    RUNNING(3, "Соревнование проводиться в данные момент."),
    COMPLETED(4, "Соревнование завершено."),
    CANCELED(5, "Соревнование отменено, т.к. не были заполнены все данные о нём к моменту его начала.");

    private static final AdjacencyMatrix<CompetitionStatus> ADJACENCY_MATRIX;

    static {
        ADJACENCY_MATRIX = AdjacencyMatrix.<CompetitionStatus>builder()
            .build();
    }

    private final int id;
    private final String description;

    public static CompetitionStatus of(int id) {
        return Arrays.stream(CompetitionStatus.values())
            .filter(status -> status.id == id)
            .findFirst()
            .orElseThrow(() -> new InvalidCompetitionStatusIdException(id));
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
