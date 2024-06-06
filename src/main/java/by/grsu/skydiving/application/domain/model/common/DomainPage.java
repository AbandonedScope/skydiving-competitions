package by.grsu.skydiving.application.domain.model.common;

import java.util.List;
import lombok.Builder;

@Builder
public record DomainPage<T>(
    long currentPage,
    int pageSize,
    int totalPages,
    List<T> content
) {
}
