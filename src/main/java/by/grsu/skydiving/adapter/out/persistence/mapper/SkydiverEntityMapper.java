package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.PassportInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.SkydiverEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.SkydiverShortInfoProjection;
import by.grsu.skydiving.application.domain.model.skydiver.Address;
import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.PhoneNumber;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface SkydiverEntityMapper {
    @Mapping(target = "beginDateOfSportCareer", source = "sportCareer.beginDateOfSportCareer")
    @Mapping(target = "sportRank", source = "sportCareer.sportRank")
    @Mapping(target = "couchName", ignore = true)
    @Mapping(target = "new", ignore = true)
    SkydiverEntity toEntity(Skydiver skydiver);

    @Mapping(target = "id", source = "userInfo.userId")
    @Mapping(target = "name.firstName", source = "userInfo.firstName")
    @Mapping(target = "name.secondName", source = "userInfo.secondName")
    @Mapping(target = "name.patronymic", source = "userInfo.patronymic")
    @Mapping(target = "sportCareer.beginDateOfSportCareer", source = "entity.beginDateOfSportCareer")
    @Mapping(target = "sportCareer.sportRank", source = "entity.sportRank")
    @Mapping(target = "passport", source = "passport")
    @Mapping(target = "couchName", ignore = true)
    Skydiver toDomain(SkydiverEntity entity, UserInfoEntity userInfo, PassportInfoEntity passport);

    @Mapping(target = "id", source = "userInfo.userId")
    @Mapping(target = "name.firstName", source = "userInfo.firstName")
    @Mapping(target = "name.secondName", source = "userInfo.secondName")
    @Mapping(target = "name.patronymic", source = "userInfo.patronymic")
    @Mapping(target = "sportCareer.beginDateOfSportCareer", source = "entity.beginDateOfSportCareer")
    @Mapping(target = "sportCareer.sportRank", source = "entity.sportRank")
    @Mapping(target = "couchName", ignore = true)
    Skydiver toExternalDomain(SkydiverEntity entity, UserInfoEntity userInfo);

    @Mapping(target = "name.firstName", source = "firstName")
    @Mapping(target = "name.secondName", source = "secondName")
    @Mapping(target = "name.patronymic", source = "patronymic")
    @Mapping(target = "sportCareer.beginDateOfSportCareer", source = "beginDateOfSportCareer")
    @Mapping(target = "sportCareer.sportRank", source = "sportRank")
    @Mapping(target = "isInternal", source = "isInternal")
    SkydiverShortInfo toDomain(SkydiverShortInfoProjection entity);

    List<SkydiverShortInfo> toDomain(List<SkydiverShortInfoProjection> entities);

    default String mapAddress(Address address) {
        return address == null
            ? null
            : address.address();
    }

    default Address mapAddress(String address) {
        return address == null
            ? null
            : new Address(address);
    }

    default String mapPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumber == null
            ? null
            : phoneNumber.number();
    }

    default PhoneNumber mapPhoneNumber(String phoneNumber) {
        return phoneNumber == null
            ? null
            : new PhoneNumber(phoneNumber);
    }

    default Integer mapSportRank(SportRank sportRank) {
        return sportRank == null
            ? null
            : sportRank.getId();
    }

    default SportRank mapSportRank(Integer id) {
        return id == null
            ? null
            : SportRank.of(id);
    }

    default Integer mapGender(Gender gender) {
        return gender == null
            ? null
            : gender.ordinal();
    }

    default Gender mapGender(Integer ordinal) {
        return ordinal == null
            ? null
            : Gender.of(ordinal);
    }
}
