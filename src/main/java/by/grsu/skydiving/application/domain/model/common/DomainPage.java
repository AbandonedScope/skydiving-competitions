package by.grsu.skydiving.application.domain.model.common;

import lombok.Builder;

import java.util.List;

@Builder
public record DomainPage<T>(
        long currentPage,
        int pageSize,
        List<T> page
) {
}
