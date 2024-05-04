package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record IndividualCompetitionSkydivers(
        Set<Skydiver> skydivers
) {
    public IndividualCompetitionSkydivers {
        validate();
    }

    public void addIndividual(Skydiver individual) {
        skydivers.add(individual);
    }

    public void removeIndividual(Skydiver individual) {
        skydivers.remove(individual);
    }

    private void validate() {
        Map<String, String> errors = new HashMap<>();

        if (skydivers == null) {
            errors.put(INDIVIDUAL_COMPETITION_SKYDIVERS_INCORRECT_VALUE_KEY, INDIVIDUAL_COMPETITION_SKYDIVERS_NULL_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
