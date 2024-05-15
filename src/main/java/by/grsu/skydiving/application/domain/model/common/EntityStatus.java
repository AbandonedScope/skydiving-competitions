package by.grsu.skydiving.application.domain.model.common;

public interface EntityStatus<T extends Enum<T> & EntityStatus<T>> {

    default void validateTransitionAvailableTo(T to, Long id) {
        AdjacencyMatrix<T> adjacencyMatrix = getAdjacencyMatrix();
        adjacencyMatrix.validateTransitionAvailable(getThis(), to, id);
    }

    AdjacencyMatrix<T> getAdjacencyMatrix();

    T getThis();
}

