package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.mapper.JumpingInfoEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.JumpingInfoJdbcRepository;
import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.domain.model.jumping.NextJumpingNumber;
import by.grsu.skydiving.application.port.out.CreateCompetitionJumpingPort;
import by.grsu.skydiving.application.port.out.DeleteJumpingPort;
import by.grsu.skydiving.application.port.out.GetCompetitionJumpingPort;
import by.grsu.skydiving.application.port.out.GetListOfJumpingForCompetitionMemberPort;
import by.grsu.skydiving.application.port.out.GetNextNumberOfJumpingPort;
import by.grsu.skydiving.application.port.out.UpdateCompetitionJumpingPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionJumpingPersistenceAdapter
    implements CreateCompetitionJumpingPort, GetNextNumberOfJumpingPort,
    GetListOfJumpingForCompetitionMemberPort, GetCompetitionJumpingPort,
    UpdateCompetitionJumpingPort, DeleteJumpingPort {
    private final JumpingInfoJdbcRepository repository;
    private final JumpingInfoEntityMapper mapper;
    @Value("${jumping.limit-per-member}")
    private int limitOfJumpingPerCompetitionMember;

    @Override
    public void create(JumpingInfo jumping) {
        var entity = mapper.toEntity(jumping);

        repository.save(entity);
    }

    @Override
    public NextJumpingNumber genNextNumberOfJumping(long competitionId, long skydiverId) {
        Integer nextJumpingNumber = repository.getNextNumberOfJumping(competitionId, skydiverId);
        boolean isLimitReached = false;

        if (nextJumpingNumber > limitOfJumpingPerCompetitionMember) {
            nextJumpingNumber = null;
            isLimitReached = true;
        }

        return new NextJumpingNumber(
            nextJumpingNumber,
            isLimitReached
        );
    }

    @Override
    public List<JumpingInfo> getList(long competitionMemberDetailsId) {
        return repository.findByCompetitionMemberDetailsIdOrderByNumberAsc(competitionMemberDetailsId).stream()
            .map(mapper::toDomain)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<JumpingInfo> getJumpingInfo(long jumpingId) {
        return repository.findById(jumpingId)
            .map(mapper::toDomain);
    }

    @Override
    public JumpingInfo update(JumpingInfo jumpingInfo) {
        var entity = mapper.toEntity(jumpingInfo);

        entity = repository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public void deleteById(long jumpingId) {
        repository.deleteById(jumpingId);
    }
}
