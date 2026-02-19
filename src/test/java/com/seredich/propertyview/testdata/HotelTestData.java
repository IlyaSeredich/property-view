package com.seredich.propertyview.testdata;

import com.seredich.propertyview.dto.*;
import com.seredich.propertyview.entity.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HotelTestData {
    public static final Long DEFAULT_ID = 1L;
    public static final String DEFAULT_NAME = "DoubleTree by Hilton Minsk";
    public static final String DEFAULT_BRAND = "Hilton";
    public static final String DEFAULT_ADDRESS = "9 Pobediteley Avenue, Minsk, 220004, Belarus";
    public static final String DEFAULT_PHONE = "+375 17 309-80-00";
    public static final String DEFAULT_EMAIL = "doubletreeminsk.info@hilton.com";
    public static final String DEFAULT_DESCRIPTION =
            "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...";
    public static final String DEFAULT_STREET = "Pobediteley Avenue";
    public static final String DEFAULT_CITY = "Minsk";
    public static final String DEFAULT_COUNTRY = "Belarus";
    public static final String DEFAULT_POSTCODE = "220004";


    private HotelTestData() {
    }

    public static HotelCreateDto hotelCreateDto() {
        return HotelCreateDto.builder()
                .name(DEFAULT_NAME)
                .description(DEFAULT_DESCRIPTION)
                .brand(DEFAULT_BRAND)
                .address(addressDto())
                .contacts(contactDto())
                .arrivalTime(arrivalTimeDto())
                .build();
    }

    public static Hotel hotelForSaving() {
        return Hotel.builder()
                .name(DEFAULT_NAME)
                .description(DEFAULT_DESCRIPTION)
                .brand(DEFAULT_BRAND)
                .address(address())
                .contacts(contact())
                .arrivalTime(arrivalTime())
                .build();
    }

    public static Hotel savedHotel() {
        return Hotel.builder()
                .id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .description(DEFAULT_DESCRIPTION)
                .brand(DEFAULT_BRAND)
                .address(address())
                .contacts(contact())
                .arrivalTime(arrivalTime())
                .amenities(new ArrayList<>())
                .build();
    }

    public static HotelSummaryDto hotelSummaryDto() {
        return HotelSummaryDto.builder()
                .id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .description(DEFAULT_DESCRIPTION)
                .address(DEFAULT_ADDRESS)
                .phone(DEFAULT_PHONE)
                .build();
    }

    public static HotelDetailDto hotelDetailDto() {
        return HotelDetailDto.builder()
                .id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .description(DEFAULT_DESCRIPTION)
                .brand(DEFAULT_BRAND)
                .address(addressDto())
                .contacts(contactDto())
                .arrivalTime(arrivalTimeDto())
                .amenities(List.of())
                .build();
    }

    public static AddressDto addressDto() {
        return AddressDto.builder()
                .houseNumber(9)
                .street(DEFAULT_STREET)
                .city(DEFAULT_CITY)
                .country(DEFAULT_COUNTRY)
                .postCode(DEFAULT_POSTCODE)
                .build();
    }

    public static ContactDto contactDto() {
        return ContactDto.builder()
                .phone(DEFAULT_PHONE)
                .email(DEFAULT_EMAIL)
                .build();
    }

    public static ArrivalTimeDto arrivalTimeDto() {
        return ArrivalTimeDto.builder()
                .checkIn(LocalTime.of(14, 0))
                .checkOut(LocalTime.of(12, 0))
                .build();
    }

    public static Address address() {
        return Address.builder()
                .houseNumber(9)
                .street(DEFAULT_STREET)
                .city(DEFAULT_CITY)
                .country(DEFAULT_COUNTRY)
                .postCode(DEFAULT_POSTCODE)
                .build();
    }

    public static Contact contact() {
        return Contact.builder()
                .phone(DEFAULT_PHONE)
                .email(DEFAULT_EMAIL)
                .build();
    }

    public static ArrivalTime arrivalTime() {
        return ArrivalTime.builder()
                .checkIn(LocalTime.of(14, 0))
                .checkOut(LocalTime.of(12, 0))
                .build();
    }

    public static List<Amenity> amenityList() {
        return List.of(
                Amenity.builder()
                        .id(DEFAULT_ID)
                        .name("Free parking")
                        .build(),
                Amenity.builder()
                        .id(2L)
                        .name("Free WiFi")
                        .build()
        );
    }
}
