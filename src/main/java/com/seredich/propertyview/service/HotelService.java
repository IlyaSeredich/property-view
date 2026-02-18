package com.seredich.propertyview.service;

import com.seredich.propertyview.dto.*;
import com.seredich.propertyview.entity.Address;
import com.seredich.propertyview.entity.Hotel;
import com.seredich.propertyview.mapper.HotelMapper;
import com.seredich.propertyview.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HotelService {
    List<HotelSummaryDto> getAllHotels();
    HotelSummaryDto createHotel(HotelCreateDto hotelCreateDto);
    HotelDetailDto getHotel(Long id);
    void addAmenities(Long id, List<String> amenities);
    List<HotelSummaryDto> searchHotels(SearchHotelDto searchHotelDto);
}
