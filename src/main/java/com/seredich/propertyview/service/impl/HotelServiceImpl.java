package com.seredich.propertyview.service.impl;

import com.seredich.propertyview.dto.HotelCreateDto;
import com.seredich.propertyview.dto.HotelDetailDto;
import com.seredich.propertyview.dto.HotelSummaryDto;
import com.seredich.propertyview.dto.SearchHotelDto;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Map<String, Long> getHistogram(String param) {
        return switch (param.toLowerCase()) {
            case "brand" -> histogramByBrand();
            case "city" -> histogramByCity();
            case "country" -> histogramByCountry();
            case "amenities" -> histogramByAmenity();
            default -> throw new RuntimeException();
        };
    }

    private Map<String, Long> histogramByAmenity() {
        List<Object[]> amenityCounts = hotelRepository.countByAmenity();
        return toMap(amenityCounts);
    }

    private Map<String, Long> histogramByCountry() {
        List<Object[]> countryCounts = hotelRepository.countByCountry();
        return toMap(countryCounts);
    }

    private Map<String, Long> histogramByCity() {
        List<Object[]> cityCounts = hotelRepository.countByCity();
        return toMap(cityCounts);
    }

    private Map<String, Long> histogramByBrand() {
        List<Object[]> brandCounts = hotelRepository.countByBrand();
        return toMap(brandCounts);
    }

    private Map<String, Long> toMap(List<Object[]> rows) {
        return rows.stream()
                .collect(Collectors.toMap(
                        key -> (String) key[0],
                        value -> (Long) value[1]
                ));
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
