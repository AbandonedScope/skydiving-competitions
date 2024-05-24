package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.MembersOfCompetition;

public interface GetMembersOfCompetitionPort {
    MembersOfCompetition getByCompetitionId(long competitionId);
}
