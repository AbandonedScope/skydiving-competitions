package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.RefereeEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.SkydiverEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.RefereeEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.mapper.UserInfoMapper;
import by.grsu.skydiving.application.domain.model.common.UserInfo;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import by.grsu.skydiving.application.port.out.DeleteRefereePort;
import by.grsu.skydiving.application.port.out.FindRefereesPort;
import by.grsu.skydiving.application.port.out.SaveRefereePort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class RefereePersistenceAdapter implements FindRefereesPort, DeleteRefereePort, SaveRefereePort {
    private final RefereeJdbcRepository refereeJdbcRepository;
    private final RefereeEntityMapper refereeEntityMapper;
    private final UserInfoJdbcRepository userInfoJdbcRepository;
    private final UserInfoMapper userInfoMapper;

    @Override
    public Optional<RefereeGroups> findRefereesByCompetitionStageId(Long competitionStageId) {
        return refereeJdbcRepository.findByCompetitionId(competitionStageId)
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
        long userId = userInfoJdbcRepository.save(userInfo).getUserId();

        UserInfo info = referee.info().withUserId(userId);
        referee = referee.withInfo(info).withId(userId);
        RefereeEntity refereeEntity = refereeEntityMapper.toEntity(referee);
        refereeEntity = refereeJdbcRepository.save(refereeEntity);

        return refereeEntity.getId();
    }
}
