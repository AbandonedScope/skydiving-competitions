package by.grsu.skydiving.adapter.in.web.request;

import java.util.List;

public record TeamRequest(
    String name,
    List<CompetitionMemberRequest> members
) {
}
