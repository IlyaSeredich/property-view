package com.seredich.propertyview.service;

import com.seredich.propertyview.dto.HotelCreateDto;
import com.seredich.propertyview.dto.HotelDetailDto;
import com.seredich.propertyview.dto.HotelSummaryDto;
import com.seredich.propertyview.dto.SearchHotelDto;

import java.util.List;
import java.util.Map;

public interface HotelService {
    List<HotelSummaryDto> getAllHotels();
    HotelSummaryDto createHotel(HotelCreateDto hotelCreateDto);
    HotelDetailDto getHotel(Long id);
    void addAmenities(Long id, List<String> amenities);
    List<HotelSummaryDto> searchHotels(SearchHotelDto searchHotelDto);
    Map<String, Long> getHistogram(String param);
}
