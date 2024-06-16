package by.grsu.skydiving.application.domain.exception.business;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import java.time.LocalDate;

public class SkydiverWithNameAndBirthDateAlreadyExistsException extends BusinessException {
    public SkydiverWithNameAndBirthDateAlreadyExistsException(FullName fullName, LocalDate localDate) {
        super("Парашютист с именем %s и датой рождения %s уже существует."
            .formatted(fullName.formatted(), localDate));
    }
}
