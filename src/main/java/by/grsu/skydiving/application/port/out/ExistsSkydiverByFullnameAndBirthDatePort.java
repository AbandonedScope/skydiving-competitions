package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;

import java.time.LocalDate;

public interface ExistsSkydiverByFullnameAndBirthDatePort {
    boolean existsBy(FullName fullName, LocalDate birthDate);
}
