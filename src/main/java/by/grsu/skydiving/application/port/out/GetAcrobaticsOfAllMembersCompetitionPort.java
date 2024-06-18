package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.pivot.AcrobaticsShortInfo;
import java.util.List;

public interface GetAcrobaticsOfAllMembersCompetitionPort {
    List<AcrobaticsShortInfo> getAcrobaticsOfAllMembers(long competitionId);
}
