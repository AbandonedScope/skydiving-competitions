package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.model.RefereeCollegium;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lombok.Builder;
import lombok.With;

@Builder
public record CompetitionCollegium(
    @With
    Long id,
    @With
    RefereeCollegium mainCollegium,
    @With
    RefereeCollegium collegium
) {
    public static class CompetitionCollegiumBuilder {
        private RefereeCollegium mainCollegium = new RefereeCollegium(HashSet.newHashSet(5));
        private RefereeCollegium collegium = new RefereeCollegium(HashSet.newHashSet(5));
    }

    public void addReferee(CollegiumReferee referee, boolean isMainCollegium) {
        mainCollegium.checkPresented(referee);
        collegium.checkPresented(referee);
        if (isMainCollegium) {
            mainCollegium.add(referee);
        } else {
            collegium.add(referee);
        }
    }

    public List<Referee> getReferees() {
        List<Referee> referees = new ArrayList<>();
        List<Referee> collegiumReferees = collegium.collegium().stream()
            .map(CollegiumReferee::referee)
            .toList();
        List<Referee> mainCollegiumReferees = mainCollegium.collegium().stream()
            .map(CollegiumReferee::referee)
            .toList();

        referees.addAll(collegiumReferees);
        referees.addAll(mainCollegiumReferees);

        return referees;
    }
}
