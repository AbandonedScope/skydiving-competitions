package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.port.in.AddExternalSkydiverUseCase;
import by.grsu.skydiving.application.port.out.ExistsSkydiverByFullnameAndBirthDatePort;
import by.grsu.skydiving.application.port.out.SaveNewSkydiverPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddExternalSkydiverService implements AddExternalSkydiverUseCase {
    private final SaveNewSkydiverPort savePort;
    private final ExistsSkydiverByFullnameAndBirthDatePort existsSkydiverByFullnameAndBirthDatePort;

    @Override
    public Skydiver addExternalSkydiver(Skydiver skydiver) {
        return savePort.saveExternal(skydiver);
    }
}
