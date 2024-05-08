package by.grsu.skydiving.application.domain.model.competition;

import java.util.Set;

public record RefereeGroups(
        Set<CollegiumReferee> mainCollegium,
        Set<CollegiumReferee> collegium) {
}
