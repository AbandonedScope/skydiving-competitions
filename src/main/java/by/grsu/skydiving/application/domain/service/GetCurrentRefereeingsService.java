package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.adapter.out.persistence.TrickSeriePersistenceAdapter;
import by.grsu.skydiving.application.domain.model.trickRefereeing.Refereeing;
import by.grsu.skydiving.application.port.in.GetRefereeingsUseCase;
import by.grsu.skydiving.common.UseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetCurrentRefereeingsService implements GetRefereeingsUseCase {
    private final TrickSeriePersistenceAdapter trickSeriePersistenceAdapter;

    @Override
    public List<Refereeing> getCurrentRefereeing(Long refereeId) {
        return trickSeriePersistenceAdapter.getCurrentRefeerings(refereeId);
    }
}
