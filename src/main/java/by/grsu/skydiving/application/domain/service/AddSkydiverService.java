package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.SkydiverWithNameAndBirthDateAlreadyExistsException;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.port.in.AddSkydiverUseCase;
import by.grsu.skydiving.application.port.out.ExistsSkydiverByFullnameAndBirthDatePort;
import by.grsu.skydiving.application.port.out.SaveNewSkydiverPort;
import by.grsu.skydiving.common.UseCase;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddSkydiverService implements AddSkydiverUseCase {
    private final SaveNewSkydiverPort savePort;
    private final ExistsSkydiverByFullnameAndBirthDatePort existsSkydiverByFullnameAndBirthDatePort;

    @Override
    public Skydiver addSkydiver(Skydiver skydiver) {
        FullName fullName = skydiver.name();
        LocalDate localDate = skydiver.birthDate();

        boolean exists = existsSkydiverByFullnameAndBirthDatePort.existsBy(fullName, localDate);
        if (exists) {
            throw new SkydiverWithNameAndBirthDateAlreadyExistsException(fullName, localDate);
        }

        return savePort.save(skydiver);
    }
}
