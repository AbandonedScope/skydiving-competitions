package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.PassportInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.SkydiverEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.SkydiverShortInfoProjection;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.UserInfoWithoutCredentials;
import by.grsu.skydiving.adapter.out.persistence.mapper.PassportInfoMapper;
import by.grsu.skydiving.adapter.out.persistence.mapper.SkydiverEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.mapper.UserInfoMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.PassportInfoJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.SkydiverJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.UserInfoJdbcRepository;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.Passport;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.port.out.ExistsSkydiverByFullnameAndBirthDatePort;
import by.grsu.skydiving.application.port.out.ExistsSkydiverByIdPort;
import by.grsu.skydiving.application.port.out.FilterSkydiversShortInfoPort;
import by.grsu.skydiving.application.port.out.FindSkydiverByIdPort;
import by.grsu.skydiving.application.port.out.GetSkydiverPagePort;
import by.grsu.skydiving.application.port.out.SaveNewSkydiverPort;
import by.grsu.skydiving.application.port.out.UpdateSkydiverPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
public class SkydiverPersistenceAdapter implements SaveNewSkydiverPort,
    ExistsSkydiverByFullnameAndBirthDatePort, GetSkydiverPagePort,
    UpdateSkydiverPort, ExistsSkydiverByIdPort, FilterSkydiversShortInfoPort,
    FindSkydiverByIdPort {
    private final SkydiverJdbcRepository skydiverJdbcRepository;
    private final UserInfoJdbcRepository userInfoJdbcRepository;
    private final PassportInfoJdbcRepository passportInfoJdbcRepository;
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
        skydiverEntity.setNew(true);
        skydiverEntity = skydiverJdbcRepository.save(skydiverEntity);
        Long skydiverId = skydiverEntity.getId();

        Passport passport = skydiver.passport();
        PassportInfoEntity passportEntity = passportInfoMapper.toEntity(passport, skydiverId);
        passportEntity = passportInfoJdbcRepository.save(passportEntity);

        return skydiverEntityMapper.toDomain(skydiverEntity, userInfo, passportEntity);
    }

    @Override
    @Transactional
    public Skydiver saveExternal(Skydiver skydiver) {
        UserInfoEntity userInfo = userInfoMapper.toEntity(skydiver);
        long userId = userInfoJdbcRepository.save(userInfo).getUserId();

        skydiver = skydiver.withId(userId);
        SkydiverEntity skydiverEntity = skydiverEntityMapper.toEntity(skydiver);
        skydiverEntity.setNew(true);
        skydiverEntity.setInternal(false);
        skydiverEntity = skydiverJdbcRepository.save(skydiverEntity);

        return skydiverEntityMapper.toExternalDomain(skydiverEntity, userInfo);
    }

    @Override
    public Optional<SkydiverShortInfo> findByIdShort(long skydiverId) {
        return skydiverJdbcRepository.findByIdShort(skydiverId)
            .map(skydiverEntityMapper::toDomain);
    }

    @Override
    public Optional<Skydiver> findById(long skydiverId) {
        return skydiverJdbcRepository.findById(skydiverId)
            .map(skydiverEntityMapper::toDomain)
            .map(this::enrichWithUserInfo)
            .map(this::enrichWithPassportInfo);
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
        return filter(HashMap.newHashMap(2), pageNumber, pageSize);
    }

    @Override
    public Skydiver updateInternal(Skydiver skydiver) {
        UserInfoEntity userInfo = userInfoMapper.toEntity(skydiver);
        userInfoJdbcRepository.save(userInfo);
        SkydiverEntity skydiverEntity = skydiverEntityMapper.toEntity(skydiver);
        skydiverEntity.setNew(false);
        skydiverEntity = skydiverJdbcRepository.save(skydiverEntity);

        Passport passport = skydiver.passport();
        PassportInfoEntity passportEntity = passportInfoMapper.toEntity(passport, skydiver.id());
        long passportId = passportInfoJdbcRepository.findBySkydiverId(skydiver.id())
            .orElseThrow()
            .getId();
        passportEntity.setId(passportId);

        passportEntity = passportInfoJdbcRepository.save(passportEntity);

        return skydiverEntityMapper.toDomain(skydiverEntity, userInfo, passportEntity);
    }

    @Override
    public Skydiver updateExternal(Skydiver skydiver) {
        UserInfoEntity userInfo = userInfoMapper.toEntity(skydiver);
        userInfoJdbcRepository.save(userInfo);

        SkydiverEntity skydiverEntity = skydiverEntityMapper.toEntity(skydiver);
        skydiverEntity.setNew(false);
        skydiverEntity.setInternal(false);
        skydiverEntity = skydiverJdbcRepository.save(skydiverEntity);

        return skydiverEntityMapper.toExternalDomain(skydiverEntity, userInfo);
    }

    @Override
    @Transactional
    public void setDeleted(long skydiverId, boolean isDeleted) {
        skydiverJdbcRepository.updateByIdSetDeleted(skydiverId, isDeleted);
        userInfoJdbcRepository.updateByIdSetDeleted(skydiverId, isDeleted);
    }

    @Override
    public boolean exists(long skydiverId) {
        return skydiverJdbcRepository.existsById(skydiverId);
    }

    @Override
    public DomainPage<SkydiverShortInfo> filter(Map<String, Object> filters, long pageNumber, int pageSize) {
        formatFilters(filters);
        long offset = pageNumber * pageSize;

        List<SkydiverShortInfoProjection> list =
            skydiverJdbcRepository.filter(filters, pageSize, offset);
        List<SkydiverShortInfo> skydiver = skydiverEntityMapper.toDomain(list);
        long totalRows = skydiverJdbcRepository.countFiltered(new HashMap<>(filters));

        int totalPages = (int) totalRows / pageSize;
        if (totalRows % pageSize > 0) {
            totalPages++;
        }

        return DomainPage.<SkydiverShortInfo>builder()
            .pageSize(pageSize)
            .currentPage(++pageNumber)
            .totalPages(totalPages)
            .content(skydiver)
            .build();
    }

    @Override
    public List<SkydiverShortInfo> filter(Map<String, Object> filters) {
        List<SkydiverShortInfoProjection> list = skydiverJdbcRepository.filter(filters);

        return skydiverEntityMapper.toDomain(list);
    }

    private void formatFilters(Map<String, Object> filters) {
        Gender gender = (Gender) filters.get("gender");
        if (gender != null) {
            filters.put("gender", gender.ordinal());
        }

        String name = (String) filters.get("name");
        if (name != null) {
            filters.put("name", name);
        }
    }

    private Skydiver enrichWithUserInfo(Skydiver skydiver) {
        UserInfoWithoutCredentials userInfo = userInfoJdbcRepository.findByUserId(skydiver.id())
            .orElseThrow();

        FullName fullName = FullName.builder()
            .firstName(userInfo.getFirstName())
            .secondName(userInfo.getSecondName())
            .patronymic(userInfo.getPatronymic())
            .build();

        return skydiver.withName(fullName);
    }

    private Skydiver enrichWithPassportInfo(Skydiver skydiver) {
        PassportInfoEntity passportInfo = passportInfoJdbcRepository.findBySkydiverId(skydiver.id())
            .orElseThrow();

        Passport passport = passportInfoMapper.toDomain(passportInfo);

        return skydiver.withPassport(passport);
    }
}
