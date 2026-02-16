package com.seredich.propertyview.controller;

import com.seredich.propertyview.dto.HotelCreateDto;
import com.seredich.propertyview.dto.HotelDetailDto;
import com.seredich.propertyview.dto.HotelSummaryDto;
import com.seredich.propertyview.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property-view")
@AllArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelSummaryDto>> getAll() {
        List<HotelSummaryDto> allHotels = hotelService.getAllHotels();
        return ResponseEntity.ok(allHotels);
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
}
