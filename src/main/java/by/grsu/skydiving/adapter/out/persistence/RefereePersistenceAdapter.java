package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.RefereeEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.RefereeProjection;
import by.grsu.skydiving.adapter.out.persistence.mapper.RefereeEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.mapper.UserInfoMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.RefereeJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.UserInfoJdbcRepository;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.UserInfo;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import by.grsu.skydiving.application.port.out.DeleteRefereePort;
import by.grsu.skydiving.application.port.out.FilterRefereesPort;
import by.grsu.skydiving.application.port.out.FindRefereesPort;
import by.grsu.skydiving.application.port.out.GetRefereeByIdPort;
import by.grsu.skydiving.application.port.out.SaveRefereePort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
public class RefereePersistenceAdapter
    implements FindRefereesPort, DeleteRefereePort,
    SaveRefereePort, FilterRefereesPort, GetRefereeByIdPort {
    private final RefereeJdbcRepository refereeJdbcRepository;
    private final RefereeEntityMapper refereeEntityMapper;
    private final UserInfoJdbcRepository userInfoJdbcRepository;
    private final UserInfoMapper userInfoMapper;

    @Override
    public Optional<RefereeGroups> findRefereesByCompetitionCollegiumId(Long competitionId) {
        return refereeJdbcRepository.findByCompetitionCollegiumId(competitionId)
            .map(refereeEntityMapper::toDomain);
    }

    @Override
    public int deleteRefereeByRefereeId(Long refereeId) {
        return refereeJdbcRepository.deleteRefereeByRefereeId(refereeId);
    }

    @Override
    @Transactional
    public Long save(Referee referee) {
        UserInfoEntity userInfo = userInfoMapper.toEntity(referee);
        userInfo = userInfoJdbcRepository.save(userInfo);
        UserInfo info = userInfoMapper.toUserInfoDomain(userInfo);
        long userId = info.userId();

        referee = referee.withId(userId);
        RefereeEntity refereeEntity = refereeEntityMapper.toEntity(referee);
        refereeEntity.setNew(true);

        refereeEntity = refereeJdbcRepository.save(refereeEntity);

        return refereeEntity.getId();
    }

    @Override
    public DomainPage<Referee> filter(Map<String, Object> filters, long pageNumber, int pageSize) {
        formatFilters(filters);
        long offset = pageNumber * pageSize;

        List<RefereeProjection> list = refereeJdbcRepository.filter(new HashMap<>(filters), pageSize, offset);
        List<Referee> referees = refereeEntityMapper.toDomains(list);
        long totalRows = refereeJdbcRepository.countFiltered(new HashMap<>(filters));

        int totalPages = (int) totalRows / pageSize;
        if (totalRows % pageSize > 0) {
            totalPages++;
        }

        return DomainPage.<Referee>builder()
            .pageSize(pageSize)
            .currentPage(++pageNumber)
            .totalPages(totalPages)
            .content(referees)
            .build();
    }

    @Override
    public Optional<Referee> getById(long refereeId) {
        return refereeJdbcRepository.findById(refereeId)
            .map(refereeEntityMapper::toDomain);
    }

    void formatFilters(Map<String, Object> filters) {
        RefereeCategory category = (RefereeCategory) filters.get("category");
        if (category != null) {
            filters.put("gender", category.ordinal());
        }

        String name = (String) filters.get("name");
        if (name != null) {
            name = "%".concat(name).concat("%");
            filters.put("name", name);
        }
    }
}
