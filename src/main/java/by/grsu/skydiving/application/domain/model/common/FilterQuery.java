package by.grsu.skydiving.application.domain.model.common;

import java.util.Map;
import lombok.Builder;

@Builder
public record FilterQuery(
    Map<String, Object> filters
) {
}
