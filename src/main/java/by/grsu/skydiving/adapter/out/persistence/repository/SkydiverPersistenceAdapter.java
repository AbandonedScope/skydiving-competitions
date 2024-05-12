package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.PassportInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.SkydiverEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.SkydiverShortInfoProjection;
import by.grsu.skydiving.adapter.out.persistence.mapper.PassportInfoMapper;
import by.grsu.skydiving.adapter.out.persistence.mapper.SkydiverEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.mapper.UserInfoMapper;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.skydiver.*;
import by.grsu.skydiving.application.port.out.*;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PersistenceAdapter
@RequiredArgsConstructor
public class SkydiverPersistenceAdapter implements SaveNewSkydiverPort,
        ExistsSkydiverByFullnameAndBirthDatePort, GetSkydiverPagePort,
        UpdateSkydiverPort, ExistsSkydiverByIdPort, FilterSkydiversShortInfoPort {
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
        return filter(HashMap.newHashMap(2), pageNumber, pageSize);
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
        fixFilters(filters);
        long offset = pageNumber * pageSize;

        List<SkydiverShortInfoProjection> list = skydiverJdbcRepository.filter(new HashMap<>(filters), pageSize, offset);
        List<SkydiverShortInfo> skydiver = skydiverEntityMapper.toDomain(list);
        long totalRows = skydiverJdbcRepository.countFiltered(new HashMap<>(filters), pageSize, offset);

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

    void fixFilters(Map<String, Object> filters) {
        Gender gender = (Gender) filters.get("gender");
        if (gender != null) {
            filters.put("gender", gender.ordinal());
        }

        String name = (String) filters.get("name");
        if (name != null) {
            name = "%".concat(name).concat("%");
            filters.put("name", name);
        }
    }
}