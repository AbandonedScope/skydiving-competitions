package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.UserNotFoundException;
import by.grsu.skydiving.application.port.in.SoftDeleteSkydiverUseCase;
import by.grsu.skydiving.application.port.out.ExistsSkydiverByIdPort;
import by.grsu.skydiving.application.port.out.UpdateSkydiverPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SoftDeleteSkydiverService implements SoftDeleteSkydiverUseCase {
    private final ExistsSkydiverByIdPort existsSkydiverByIdPort;
    private final UpdateSkydiverPort updateSkydiverPort;

    @Override
    public void softDelete(long skydiverId) {
        if (!existsSkydiverByIdPort.exists(skydiverId)) {
            throw new UserNotFoundException(skydiverId);
        }

        updateSkydiverPort.setDeleted(skydiverId, true);
    }
}
