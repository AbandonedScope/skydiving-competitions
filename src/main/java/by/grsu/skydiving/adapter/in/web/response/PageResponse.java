package by.grsu.skydiving.adapter.in.web.response;

import java.util.List;

public record PageResponse<T>(
        Long currentPage,
        Integer pageSize,
        List<T> page
) {
}
