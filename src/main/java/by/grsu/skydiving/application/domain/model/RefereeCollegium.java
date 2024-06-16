package by.grsu.skydiving.application.domain.model;

import by.grsu.skydiving.application.domain.exception.domain.RefereeAlreadyPresentedInCollegium;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import java.util.Set;

public record RefereeCollegium(
    Set<CollegiumReferee> collegium
) {
    public void checkPresented(CollegiumReferee referee) {
        long refereeId = referee.referee().id();
        if (collegium.stream()
            .anyMatch(collegiumReferee -> collegiumReferee.referee().id() == refereeId)) {
            throw new RefereeAlreadyPresentedInCollegium(refereeId);
        }
    }

    public void add(CollegiumReferee referee) {
        checkPresented(referee);

        collegium.add(referee);
    }
}
