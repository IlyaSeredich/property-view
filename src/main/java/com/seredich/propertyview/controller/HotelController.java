package com.seredich.propertyview.controller;

import com.seredich.propertyview.dto.*;
import com.seredich.propertyview.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@AllArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelSummaryDto>> getAll() {
        List<HotelSummaryDto> hotelSummaryDtoList = hotelService.getAllHotels();
        return ResponseEntity.ok(hotelSummaryDtoList);
    }

    @PostMapping("/hotels")
    public ResponseEntity<HotelSummaryDto> create(@RequestBody HotelCreateDto hotelCreateDto) {
        HotelSummaryDto hotelSummaryDto = hotelService.createHotel(hotelCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelSummaryDto);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDetailDto> get(@PathVariable Long id) {
        HotelDetailDto hotelDetailDto = hotelService.getHotel(id);
        return ResponseEntity.ok(hotelDetailDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<HotelSummaryDto>> search(@ModelAttribute SearchHotelDto searchHotelDto) {
        List<HotelSummaryDto> hotelSummaryDtoList = hotelService.searchHotels(searchHotelDto);
        return ResponseEntity.ok(hotelSummaryDtoList);
    }

    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHistogram(@PathVariable String param) {
        Map<String, Long> histogramMap = hotelService.getHistogram(param);
        return ResponseEntity.ok(histogramMap);

    }

    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<Void> addAmenities(@PathVariable Long id, @RequestBody List<String> amenityNamesToAdd) {
        hotelService.addAmenities(id, amenityNamesToAdd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
