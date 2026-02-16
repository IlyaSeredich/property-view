package com.seredich.propertyview.service.impl;

import com.seredich.propertyview.dto.HotelCreateDto;
import com.seredich.propertyview.dto.HotelDetailDto;
import com.seredich.propertyview.dto.HotelSummaryDto;
import com.seredich.propertyview.entity.Address;
import com.seredich.propertyview.entity.Hotel;
import com.seredich.propertyview.mapper.HotelMapper;
import com.seredich.propertyview.repository.HotelRepository;
import com.seredich.propertyview.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public List<HotelSummaryDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return mapToSummaryDtoList(hotels);
    }

    @Override
    public HotelSummaryDto createHotel(HotelCreateDto hotelCreateDto) {
        Hotel hotel = hotelMapper.toEntity(hotelCreateDto);
        Hotel savedHotel = hotelRepository.save(hotel);
        return mapToSummaryDto(savedHotel);
    }

    @Override
    public HotelDetailDto getHotel(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotelMapper.toDetailDto(hotel.get());
    }

    private List<HotelSummaryDto> mapToSummaryDtoList(List<Hotel> hotels) {
        return hotels.stream().map(this::mapToSummaryDto).toList();
    }

    private HotelSummaryDto mapToSummaryDto(Hotel hotel) {
        String addressStr = createAddressStr(hotel.getAddress());
        return hotelMapper.toSummaryDto(hotel, addressStr);
    }

    private String createAddressStr(Address address) {
        StringBuilder result = new StringBuilder();
        result.append(address.getHouseNumber()).append(" ").append(address.getStreet()).append(", ")
                .append(address.getCity()).append(", ").append(address.getPostCode()).append(", ")
                .append(address.getCountry());
        return result.toString();
    }
}
