package by.grsu.skydiving.application.domain.model.common;

import by.grsu.skydiving.application.domain.exception.TransitionUnavailableException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AdjacencyMatrix<T extends Enum<T> & EntityStatus<T>> {

    private final Map<T, List<T>> matrix;

    private AdjacencyMatrix(Map<T, List<T>> matrix) {
        this.matrix = matrix;
    }

    public static <T extends Enum<T> & EntityStatus<T>> Builder<T> builder() {
        return new Builder<>();
    }

    public List<T> getAvailableTransitionsFromOrError(T from) {
        List<T> availableTransitionsFrom = getAvailableTransitionsFromOrEmpty(from);

        if (availableTransitionsFrom.isEmpty()) {
            throw new TransitionUnavailableException(
                "No transition available for from " + from,
                from
            );
        }

        return new ArrayList<>(availableTransitionsFrom);
    }

    public List<T> getAvailableTransitionsFromOrEmpty(T from) {
        return matrix.getOrDefault(from, Collections.emptyList());
    }

    public void validateTransitionAvailable(T from, T to, Long id) {
        if (!matrix.containsKey(from)) {
            throw new TransitionUnavailableException(
                "Key " + from + " is not present in adjacency matrix, id " + id,
                from,
                to,
                id
            );
        }

        List<T> possibleTransitions = getAvailableTransitionsFromOrEmpty(from);
        if (!possibleTransitions.contains(to)) {
            throw new TransitionUnavailableException(
                "No transition available for from " + from + " to " + to,
                from,
                to,
                possibleTransitions,
                id
            );
        }
    }

    public static final class Builder<T extends Enum<T> & EntityStatus<T>> {

        private final Map<T, List<T>> adjacencyMatrix = new HashMap<>();

        public Builder<T> addTransition(T from, T to) {
            validateKeyIsNotPresent(from);
            adjacencyMatrix.put(from, List.of(to));
            return this;
        }

        @SafeVarargs
        public final Builder<T> addTransition(T from, T to, T... tos) {
            validateKeyIsNotPresent(from);

            if (tos == null || tos.length == 0) {
                throw new IllegalArgumentException(
                    "Wrong method usage when trying to add transition for from " + from + " to " + to);
            }

            T[] allStateTransitions = ArrayUtils.addToArray(tos, to);
            adjacencyMatrix.put(from, List.of(allStateTransitions));

            return this;
        }

        private void validateKeyIsNotPresent(T from) {
            if (adjacencyMatrix.containsKey(from)) {
                throw new IllegalArgumentException("Key " + from + " is already present");
            }
        }

        public AdjacencyMatrix<T> build() {
            return new AdjacencyMatrix<>(adjacencyMatrix);
        }
    }
}
