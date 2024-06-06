package by.grsu.skydiving.application.domain.model.skydiver;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.JOB_NAME_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.JOB_NAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.JOB_POSITION_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.JOB_POSITION_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import java.util.HashMap;
import java.util.Map;

public record Job(
    String jobName,
    String jobPosition,
    PhoneNumber jobPhoneNumber
) {
    public Job {
        validate(jobName, jobPosition);
    }

    private void validate(String jobName, String jobPosition) {
        Map<String, String> errors = new HashMap<>();

        if (jobName == null || jobName.isBlank() || jobName.length() > 50) {
            errors.put(JOB_NAME_INCORRECT_VALUE_KEY, JOB_NAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (jobPosition == null || jobPosition.isBlank() || jobPosition.length() > 50) {
            errors.put(JOB_POSITION_INCORRECT_VALUE_KEY, JOB_POSITION_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
