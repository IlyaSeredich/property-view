package com.seredich.propertyview.service.impl;

import com.seredich.propertyview.dto.*;
import com.seredich.propertyview.entity.Address;
import com.seredich.propertyview.entity.Amenity;
import com.seredich.propertyview.entity.Hotel;
import com.seredich.propertyview.mapper.HotelMapper;
import com.seredich.propertyview.repository.HotelRepository;
import com.seredich.propertyview.service.AmenityService;
import com.seredich.propertyview.service.HotelService;
import com.seredich.propertyview.specification.HotelSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final AmenityService amenityService;

    @Override
    public List<HotelSummaryDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return mapToSummaryDtoList(hotels);
    }

    @Override
    @Transactional
    public HotelSummaryDto createHotel(HotelCreateDto hotelCreateDto) {
        Hotel hotel = hotelMapper.toEntity(hotelCreateDto);
        Hotel savedHotel = hotelRepository.save(hotel);
        return mapToSummaryDto(savedHotel);
    }

    @Override
    public HotelDetailDto getHotel(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        List<String> amenityNamesList = amenityService.getAmenityNameList(hotel.get().getAmenities());
        return hotelMapper.toDetailDto(hotel.get(), amenityNamesList);
    }

    @Override
    @Transactional
    public void addAmenities(Long id, List<String> amenityNamesToAdd) {
        Hotel hotel = hotelRepository.findById(id).get();
        List<Amenity> hotelsAmenities = hotel.getAmenities();
        List<Amenity> preparedAmenities = amenityService.getPreparedAmenities(hotelsAmenities, amenityNamesToAdd);
        hotelsAmenities.addAll(preparedAmenities);
    }

    @Override
    public List<HotelSummaryDto> searchHotels(SearchHotelDto searchHotelDto) {
        Specification<Hotel> specification = HotelSpecification.build(searchHotelDto);
        List<Hotel> searchedHotels = hotelRepository.findAll(specification);
        return mapToSummaryDtoList(searchedHotels);
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
