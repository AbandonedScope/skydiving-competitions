package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.SkydiverNotFoundException;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.port.in.GetSkydiverUseCase;
import by.grsu.skydiving.application.port.out.FindSkydiverByIdPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetSkydiverService implements GetSkydiverUseCase {
    private final FindSkydiverByIdPort findSkydiverByIdPort;

    @Override
    public SkydiverShortInfo getByIdShort(long skydiverId) {
        return findSkydiverByIdPort.findByIdShort(skydiverId)
            .orElseThrow(() -> new SkydiverNotFoundException(skydiverId));
    }

    @Override
    public Skydiver getById(long skydiverId) {
        return findSkydiverByIdPort.findById(skydiverId)
            .orElseThrow(() -> new SkydiverNotFoundException(skydiverId));
    }
}
