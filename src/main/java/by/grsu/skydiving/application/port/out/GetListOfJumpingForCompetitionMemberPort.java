package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import java.util.List;

public interface GetListOfJumpingForCompetitionMemberPort {
    List<JumpingInfo> getList(long competitionMemberDetailsId);
}
