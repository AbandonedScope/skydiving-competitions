package by.grsu.skydiving.application.domain.exception.business;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;

import java.time.LocalDate;

public class SkydiverWithNameAndBirthDateAlreadyExistsException extends BusinessException {
    public SkydiverWithNameAndBirthDateAlreadyExistsException(FullName fullName, LocalDate localDate) {
        super("Skydiver with name %s and birth date %s already exists."
                .formatted(fullName.formatted(), localDate));
    }
}
