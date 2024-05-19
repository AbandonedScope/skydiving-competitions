package by.grsu.skydiving.application.domain.model.common;

import lombok.Builder;

@Builder
public record GetPageQuery<F>(
        long pageNumber,
        int pageSize,
        F filterQuery
) {
}