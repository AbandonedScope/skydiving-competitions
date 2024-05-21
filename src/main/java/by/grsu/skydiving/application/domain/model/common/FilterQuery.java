package by.grsu.skydiving.application.domain.model.common;
import lombok.Builder;

import java.util.Map;

@Builder
public record FilterQuery(
        Map<String, Object> filters
) {
}
