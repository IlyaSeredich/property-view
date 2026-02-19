package com.seredich.propertyview.testdata;

import com.seredich.propertyview.dto.*;
import com.seredich.propertyview.entity.Address;
import com.seredich.propertyview.entity.ArrivalTime;
import com.seredich.propertyview.entity.Contact;
import com.seredich.propertyview.entity.Hotel;

import java.time.LocalTime;
import java.util.List;

public class HotelTestData {

    private HotelTestData() {
    }

    public static HotelCreateDto hotelCreateDto() {
        return HotelCreateDto.builder()
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .brand("Hilton")
                .address(AddressDto.builder()
                        .houseNumber(9)
                        .street("Pobediteley Avenue")
                        .city("Minsk")
                        .country("Belarus")
                        .postCode("220004")
                        .build())
                .contacts(ContactDto.builder()
                        .phone("+36 1 123 4567")
                        .email("doubletreeminsk.info@hilton.com")
                        .build())
                .arrivalTime(ArrivalTimeDto.builder()
                        .checkIn(LocalTime.of(14, 0))
                        .checkOut(LocalTime.of(12, 0))
                        .build())
                .build();
    }

    public static Hotel hotelForSaving() {
        return Hotel.builder()
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .brand("Hilton")
                .address(Address.builder()
                        .houseNumber(9)
                        .street("Pobediteley Avenue")
                        .city("Minsk")
                        .country("Belarus")
                        .postCode("220004")
                        .build())
                .contacts(Contact.builder()
                        .phone("+36 1 123 4567")
                        .email("doubletreeminsk.info@hilton.com")
                        .build())
                .arrivalTime(ArrivalTime.builder()
                        .checkIn(LocalTime.of(14, 0))
                        .checkOut(LocalTime.of(12, 0))
                        .build())
                .build();
    }

    public static Hotel savedHotel() {
        return Hotel.builder()
                .id(1L)
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .brand("Hilton")
                .address(Address.builder()
                        .houseNumber(9)
                        .street("Pobediteley Avenue")
                        .city("Minsk")
                        .country("Belarus")
                        .postCode("220004")
                        .build())
                .contacts(Contact.builder()
                        .phone("+36 1 123 4567")
                        .email("doubletreeminsk.info@hilton.com")
                        .build())
                .arrivalTime(ArrivalTime.builder()
                        .checkIn(LocalTime.of(14, 0))
                        .checkOut(LocalTime.of(12, 0))
                        .build())
                .build();
    }

    public static HotelSummaryDto hotelSummaryDto() {
        return HotelSummaryDto.builder()
                .id(1L)
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .address("9 Pobediteley Avenue, Minsk, 220004, Belarus")
                .phone("+375 17 309-80-00")
                .build();
    }

    public static HotelDetailDto hotelDetailDto() {
        return HotelDetailDto.builder()
                .id(1L)
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .brand("Hilton")
                .address(AddressDto.builder()
                        .houseNumber(9)
                        .street("Pobediteley Avenue")
                        .city("Minsk")
                        .country("Belarus")
                        .postCode("220004")
                        .build())
                .contacts(ContactDto.builder()
                        .phone("+36 1 123 4567")
                        .email("doubletreeminsk.info@hilton.com")
                        .build())
                .arrivalTime(ArrivalTimeDto.builder()
                        .checkIn(LocalTime.of(14, 0))
                        .checkOut(LocalTime.of(12, 0))
                        .build())
                .amenities(List.of())
                .build();
    }
}
