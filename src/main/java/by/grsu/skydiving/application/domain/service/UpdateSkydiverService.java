package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.UpdateExternalSkydiverException;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.port.in.GetSkydiverUseCase;
import by.grsu.skydiving.application.port.in.UpdateExternalSkydiverUseCase;
import by.grsu.skydiving.application.port.in.UpdateSkydiverUseCase;
import by.grsu.skydiving.application.port.out.UpdateSkydiverPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateSkydiverService implements UpdateSkydiverUseCase,
    UpdateExternalSkydiverUseCase {
    private final GetSkydiverUseCase getSkydiverUseCase;
    private final UpdateSkydiverPort updateSkydiverPort;

    @Override
    public Skydiver updateInternal(Long skydiverId, Skydiver skydiver) {
        getSkydiverUseCase.getById(skydiverId);

        skydiver = skydiver.withId(skydiverId);
        return updateSkydiverPort.updateInternal(skydiver);
    }

    @Override
    public Skydiver updateExternal(Long skydiverId, Skydiver skydiver) {
        SkydiverShortInfo oldSkydiverInfo = getSkydiverUseCase.getByIdShort(skydiverId);
        if (oldSkydiverInfo.isInternal()) {
            throw new UpdateExternalSkydiverException(skydiverId);
        }

        Skydiver newSkydiverInfo = Skydiver.builder()
            .id(skydiverId)
            .name(skydiver.name())
            .sportCareer(skydiver.sportCareer())
            .gender(skydiver.gender())
            .build();

        return updateSkydiverPort.updateExternal(newSkydiverInfo);
    }
}
