package com.seredich.propertyview.mapper;

import com.seredich.propertyview.dto.HotelCreateDto;
import com.seredich.propertyview.dto.HotelDetailDto;
import com.seredich.propertyview.dto.HotelSummaryDto;
import com.seredich.propertyview.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(target = "address", source = "addressStr")
    @Mapping(target = "phone", source = "hotel.contacts.phone")
    HotelSummaryDto toSummaryDto(Hotel hotel, String addressStr);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    @Mapping(target = "contacts.phone", source = "contacts.phone")
    @Mapping(target = "contacts.email", source = "contacts.email")
    Hotel toEntity(HotelCreateDto hotelCreateDto);

    @Mapping(target = "address.houseNumber", source = "hotel.address.houseNumber")
    @Mapping(target = "address.street", source = "hotel.address.street")
    @Mapping(target = "address.city", source = "hotel.address.city")
    @Mapping(target = "address.country", source = "hotel.address.country")
    @Mapping(target = "address.postCode", source = "hotel.address.postCode")
    @Mapping(target = "contacts.phone", source = "hotel.contacts.phone")
    @Mapping(target = "contacts.email", source = "hotel.contacts.email")
    @Mapping(target = "arrivalTime.checkIn", source = "hotel.arrivalTime.checkIn")
    @Mapping(target = "arrivalTime.checkOut", source = "hotel.arrivalTime.checkOut")
    @Mapping(target = "amenities", source = "amenityNamesList")
    HotelDetailDto toDetailDto(Hotel hotel, List<String> amenityNamesList);
}
