package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.port.in.GetNextMemberNumberAndUpdateUseCase;
import by.grsu.skydiving.application.port.out.GetNextMemberNumberAndIncrementPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetNextMemberNumberAndUpdateService implements GetNextMemberNumberAndUpdateUseCase {
    private final GetNextMemberNumberAndIncrementPort getNextMemberNumberAndIncrementPort;

    @Override
    public int getMemberMemberAndUpdate(Long competitionId) {
        return 0;
    }
}
