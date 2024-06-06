package by.grsu.skydiving.application.domain.model;

import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import java.util.Set;

public record RefereeCollegium(
    Set<CollegiumReferee> collegium
) {
}
