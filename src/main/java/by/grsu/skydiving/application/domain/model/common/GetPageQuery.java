package by.grsu.skydiving.application.domain.model.common;

import lombok.Builder;

@Builder
public record GetPageQuery(
    long pageNumber,
    int pageSize,
    FilterQuery filterQuery
) {
}