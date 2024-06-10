package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.ExternalSkydiverRequest;
import by.grsu.skydiving.adapter.in.web.request.PassportDetailsRequest;
import by.grsu.skydiving.adapter.in.web.request.SkydiverRequest;
import by.grsu.skydiving.adapter.in.web.response.ClothingSizeResponse;
import by.grsu.skydiving.adapter.in.web.response.FullNameResponse;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.adapter.in.web.response.PassportDetailsResponse;
import by.grsu.skydiving.adapter.in.web.response.SkydiverResponse;
import by.grsu.skydiving.adapter.in.web.response.SkydiverShortInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.SportCareerResponse;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.skydiver.Address;
import by.grsu.skydiving.application.domain.model.skydiver.ClothingSize;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import by.grsu.skydiving.application.domain.model.skydiver.Passport;
import by.grsu.skydiving.application.domain.model.skydiver.PhoneNumber;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.domain.model.skydiver.SportCareer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface SkydiverMapper {
    @Mapping(target = "name.firstName", source = "firstName")
    @Mapping(target = "name.secondName", source = "secondName")
    @Mapping(target = "name.patronymic", source = "patronymic")
    @Mapping(target = "passport", source = "passportDetails")
    @Mapping(target = "sportCareer.sportRank", source = "sportRank")
    @Mapping(target = "phoneNumber", source = "phone")
    Skydiver toDomain(SkydiverRequest request);

    @Mapping(target = "name.firstName", source = "firstName")
    @Mapping(target = "name.secondName", source = "secondName")
    @Mapping(target = "name.patronymic", source = "patronymic")
    @Mapping(target = "sportCareer.sportRank", source = "sportRank")
    Skydiver toDomain(ExternalSkydiverRequest request);

    Passport toDomain(PassportDetailsRequest request);

    @Mapping(target = "height", source = "height.height")
    @Mapping(target = "weight", source = "weight.weight")
    SkydiverResponse toResponse(Skydiver skydiver);

    @Mapping(target = "sportRank", source = "sportCareer.sportRank")
    SkydiverShortInfoResponse toResponse(SkydiverShortInfo shortInfo);

    PageResponse<SkydiverShortInfoResponse> toResponse(DomainPage<SkydiverShortInfo> domainPage);

    PassportDetailsResponse toResponse(Passport passport);

    SportCareerResponse toResponse(SportCareer sportCareer);

    FullNameResponse toResponse(FullName name);

    ClothingSizeResponse toResponse(ClothingSize clothingSize);

    default PhoneNumber mapPhoneNumber(String phoneNumber) {
        return phoneNumber == null
            ? null
            : new PhoneNumber(phoneNumber);
    }

    default String mapPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumber == null
            ? null
            : phoneNumber.number();
    }

    default Address mapAddress(String address) {
        return address == null
            ? null
            : new Address(address);
    }

    default String mapAddress(Address address) {
        return address == null
            ? null
            : address.address();
    }
}
