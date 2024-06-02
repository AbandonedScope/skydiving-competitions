package by.grsu.skydiving.adapter.in.web.response;

import java.util.List;

public record CompetitionMemberJumpingResponse(
    long competitionId,
    long skydiverId,
    long competitionMemberId,
    List<JumpingInfoResponse> jumping
) {
}
