package by.grsu.skydiving.application.domain.model.common;

public record GetPageQuery(
       long pageNumber,
       int pageSize
    ) {
    }