package by.grsu.skydiving.application.domain.model.skydiver;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.BEGIN_DATE_OF_SPORT_CAREER_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.BEGIN_DATE_OF_SPORT_IS_NULL_OR_INCORRECT_DATE_MESSAGE;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.SPORT_SPECIALIZATION_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.SPORT_SPECIALIZATION_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public record SportCareer(
    LocalDate beginDateOfSportCareer,
    String sportSpecialization,
    SportDegree sportDegree
) {
    public SportCareer {
        validate(beginDateOfSportCareer, sportSpecialization);
    }

    private void validate(LocalDate beginDateOfSportCareer, String sportSpecialization) {
        Map<String, String> errors = new HashMap<>();

        if (beginDateOfSportCareer != null && beginDateOfSportCareer.isAfter(LocalDate.now())) {
            errors.put(BEGIN_DATE_OF_SPORT_CAREER_INCORRECT_VALUE_KEY,
                BEGIN_DATE_OF_SPORT_IS_NULL_OR_INCORRECT_DATE_MESSAGE);
        }

        if (sportSpecialization != null && !sportSpecialization.isBlank() && sportSpecialization.length() > 50) {
            errors.put(SPORT_SPECIALIZATION_INCORRECT_VALUE_KEY,
                SPORT_SPECIALIZATION_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
