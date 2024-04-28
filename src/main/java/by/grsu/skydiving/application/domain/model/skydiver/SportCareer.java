package by.grsu.skydiving.application.domain.model.skydiver;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record SportCareer (
       LocalDate beginDateOfSportCareer,
       String sportSpecialization,
       String sportDegree
){
    public SportCareer{
        validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

        if (beginDateOfSportCareer != null && beginDateOfSportCareer.isAfter(LocalDate.now())) {
            errors.put(BEGIN_DATE_OF_SPORT_CAREER_INCORRECT_VALUE_KEY, BEGIN_DATE_OF_SPORT_IS_NULL_OR_INCORRECT_DATE_MESSAGE);
        }

        if(sportSpecialization != null && !sportSpecialization.isBlank() && sportSpecialization.length() > 50) {
            errors.put(SPORT_SPECIALIZATION_INCORRECT_VALUE_KEY, SPORT_SPECIALIZATION_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (sportDegree != null && !sportDegree.isBlank() && sportDegree.length() > 50) {
            errors.put(SPORT_DEGREE_INCORRECT_VALUE_KEY, SPORT_DEGREE_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .errors(errors)
                    .build();
        }
    }
}
