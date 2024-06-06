package by.grsu.skydiving.application.domain.model.competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record RefereeGroups(
    Set<CollegiumReferee> mainCollegium,
    Set<CollegiumReferee> collegium
) {
    public List<Referee> getReferees() {
        List<Referee> referees = new ArrayList<>();
        List<Referee> collegiumReferees = collegium.stream()
            .map(CollegiumReferee::referee)
            .toList();
        List<Referee> mainCollegiumReferees = mainCollegium.stream()
            .map(CollegiumReferee::referee)
            .toList();

        referees.addAll(collegiumReferees);
        referees.addAll(mainCollegiumReferees);

        return referees;
    }
}
