package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;

public interface UpdateCompetitionMemberPort {
    CompetitionMember update(CompetitionMember competitionMember);
}
