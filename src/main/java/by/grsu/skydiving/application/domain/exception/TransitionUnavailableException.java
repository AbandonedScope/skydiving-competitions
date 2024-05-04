package by.grsu.skydiving.application.domain.exception;

import by.grsu.skydiving.application.domain.exception.domain.DomainException;

import java.util.Collections;
import java.util.List;

public class TransitionUnavailableException extends DomainException {

    private final Enum<?> from;
    private final Enum<?> to;
    private final List<? extends Enum<?>> availableTransfers;
    private final Long id;

    public TransitionUnavailableException(
            String message,
            Enum<?> from,
            Enum<?> to,
            List<? extends Enum<?>> availableTransfers,
            Long id
    ) {
        super(message);
        this.from = from;
        this.to = to;
        this.availableTransfers = availableTransfers;
        this.id = id;
    }

    public TransitionUnavailableException(
            String message,
            Enum<?> from,
            Enum<?> to,
            Long id
    ) {
        super(message);
        this.from = from;
        this.to = to;
        this.availableTransfers = Collections.emptyList();
        this.id = id;
    }

    public TransitionUnavailableException(
            String message,
            Enum<?> from
    ) {
        super(message);
        this.from = from;
        this.to = null;
        this.availableTransfers = Collections.emptyList();
        this.id = null;
    }
}
