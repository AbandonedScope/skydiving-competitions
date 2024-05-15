package by.grsu.skydiving.adapter.in.web.response;

import java.util.Set;

public record RefereeGroupsResponse(
        Set<RefereeResponse> mainCollegium,
        Set<RefereeResponse> collegium
) {
}
