package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.PassportInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.SkydiverEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.SkydiverShortInfoProjection;
import by.grsu.skydiving.application.domain.model.skydiver.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface SkydiverEntityMapper {
    @Mapping(target = "height", source = "height.height")
    @Mapping(target = "weight", source = "weight.weight")
    @Mapping(target = "shoeSize", source = "clothingSize.shoeSize")
    @Mapping(target = "jacketSize", source = "clothingSize.jacketSize")
    @Mapping(target = "pantsSize", source = "clothingSize.pantsSize")
    @Mapping(target = "beginDateOfSportCareer", source = "sportCareer.beginDateOfSportCareer")
    @Mapping(target = "sportSpecialization", source = "sportCareer.sportSpecialization")
    @Mapping(target = "sportDegree", source = "sportCareer.sportDegree")
    @Mapping(target = "couchName", ignore = true)
    @Mapping(target = "new", constant = "true")
    SkydiverEntity toEntity(Skydiver skydiver);

    @Mapping(target = "id", source = "userInfo.userId")
    @Mapping(target = "name.firstName", source = "userInfo.firstName")
    @Mapping(target = "name.secondName", source = "userInfo.secondName")
    @Mapping(target = "name.patronymic", source = "userInfo.patronymic")
    @Mapping(target = "height", source = "entity.height")
    @Mapping(target = "weight", source = "entity.weight")
    @Mapping(target = "clothingSize.shoeSize", source = "entity.shoeSize")
    @Mapping(target = "clothingSize.jacketSize", source = "entity.jacketSize")
    @Mapping(target = "clothingSize.pantsSize", source = "entity.pantsSize")
    @Mapping(target = "sportCareer.beginDateOfSportCareer", source = "entity.beginDateOfSportCareer")
    @Mapping(target = "sportCareer.sportSpecialization", source = "entity.sportSpecialization")
    @Mapping(target = "sportCareer.sportDegree", source = "entity.sportDegree")
    @Mapping(target = "passport", source = "passport")
    @Mapping(target = "couchName", ignore = true)
    Skydiver toDomain(SkydiverEntity entity, UserInfoEntity userInfo, PassportInfoEntity passport);

    @Mapping(target = "name.firstName", source = "firstName")
    @Mapping(target = "name.secondName", source = "secondName")
    @Mapping(target = "name.patronymic", source = "patronymic")
    @Mapping(target = "sportCareer.beginDateOfSportCareer", source = "beginDateOfSportCareer")
    @Mapping(target = "sportCareer.sportSpecialization", source = "sportSpecialization")
    @Mapping(target = "sportCareer.sportDegree", source = "sportDegree")
    SkydiverShortInfo toDomain(SkydiverShortInfoProjection entity);

    List<SkydiverShortInfo> toDomain(List<SkydiverShortInfoProjection> entities);

    default Height mapHeight(Float height) {
        return height == null
                ? null
                : new Height(height);
    }

    default Weight mapWeight(Float weight) {
        return weight == null
                ? null
                : new Weight(weight);
    }

    default String mapAddress(Address address) {
        return address.address();
    }

    default Address mapAddress(String address) {
        return new Address(address);
    }

    default String mapPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumber.number();
    }

    default PhoneNumber mapPhoneNumber(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    default Integer mapSportDegree(SportDegree sportDegree) {
        return sportDegree == null
                ? null
                : sportDegree.ordinal();
    }

    default SportDegree mapSportDegree(Integer ordinal) {
        return ordinal == null
                ? null
                : SportDegree.of(ordinal);
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
