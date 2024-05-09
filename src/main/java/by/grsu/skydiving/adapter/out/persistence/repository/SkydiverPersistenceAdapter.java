package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.UserInfoJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.entity.PassportInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.SkydiverEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.SkydiverShortInfoProjection;
import by.grsu.skydiving.adapter.out.persistence.mapper.PassportInfoMapper;
import by.grsu.skydiving.adapter.out.persistence.mapper.SkydiverEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.mapper.UserInfoMapper;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import by.grsu.skydiving.application.domain.model.skydiver.Passport;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.port.out.ExistsSkydiverByFullnameAndBirthDatePort;
import by.grsu.skydiving.application.port.out.GetSkydiverPagePort;
import by.grsu.skydiving.application.port.out.SaveNewSkydiverPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class SkydiverPersistenceAdapter implements SaveNewSkydiverPort,
        ExistsSkydiverByFullnameAndBirthDatePort, GetSkydiverPagePort {
    private final SkydiverJdbcRepository skydiverJdbcRepository;
    private final UserInfoJdbcRepository userInfoJdbcRepository;
    private final PasswordInfoJdbcRepository passwordInfoJdbcRepository;
    private final SkydiverEntityMapper skydiverEntityMapper;
    private final UserInfoMapper userInfoMapper;
    private final PassportInfoMapper passportInfoMapper;

    @Override
    @Transactional
    public Skydiver save(Skydiver skydiver) {
        UserInfoEntity userInfo = userInfoMapper.toEntity(skydiver);
        long userId = userInfoJdbcRepository.save(userInfo).getUserId();

        skydiver = skydiver.withId(userId);
        SkydiverEntity skydiverEntity = skydiverEntityMapper.toEntity(skydiver);
        skydiverEntity = skydiverJdbcRepository.save(skydiverEntity);
        Long skydiverId = skydiverEntity.getId();

        Passport passport = skydiver.passport();
        PassportInfoEntity passportEntity = passportInfoMapper.toEntity(passport, skydiverId);
        passportEntity = passwordInfoJdbcRepository.save(passportEntity);

        return skydiverEntityMapper.toDomain(skydiverEntity, userInfo, passportEntity);
    }

    @Override
    public boolean existsBy(FullName fullName, LocalDate birthDate) {
        return skydiverJdbcRepository.findByBirthDateAndFullName(
                fullName.firstName(),
                fullName.secondName(),
                fullName.patronymic(),
                birthDate
        );
    }

    @Override
    public DomainPage<SkydiverShortInfo> getPage(long pageNumber, int pageSize) {
        long offset = pageNumber * pageSize;
        List<SkydiverShortInfoProjection> list = skydiverJdbcRepository.getPage(pageSize, offset);
        List<SkydiverShortInfo> skydiver = skydiverEntityMapper.toDomain(list);

        return DomainPage.<SkydiverShortInfo>builder()
                .pageSize(pageSize)
                .currentPage(++pageNumber)
                .page(skydiver)
                .build();
    }
}
