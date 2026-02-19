package com.seredich.propertyview.controller;

import com.seredich.propertyview.dto.HotelCreateDto;
import com.seredich.propertyview.dto.HotelDetailDto;
import com.seredich.propertyview.dto.HotelSummaryDto;
import com.seredich.propertyview.dto.SearchHotelDto;
import com.seredich.propertyview.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@AllArgsConstructor
@Tag(name = "Hotel API", description = "Endpoints for actions with hotels")
public class HotelController {
    private final HotelService hotelService;

    @Operation(
            summary = "Get all hotels",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns summary view for every registered hotel",
                            content = @Content(
                                    mediaType = "application/json")),
            }
    )
    @GetMapping("/hotels")
    public ResponseEntity<List<HotelSummaryDto>> getAll() {
        List<HotelSummaryDto> hotelSummaryDtoList = hotelService.getAllHotels();
        return ResponseEntity.ok(hotelSummaryDtoList);
    }

    @Operation(
            summary = "Add new hotel",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Returns summary view for created hotel",
                            content = @Content(
                                    mediaType = "application/json")),
            }
    )
    @PostMapping("/hotels")
    public ResponseEntity<HotelSummaryDto> create(@RequestBody HotelCreateDto hotelCreateDto) {
        HotelSummaryDto hotelSummaryDto = hotelService.createHotel(hotelCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelSummaryDto);
    }

    @Operation(
            summary = "Get hotel by id",
            parameters = {@Parameter(name = "id", in = ParameterIn.PATH)},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns detail view for found hotel",
                            content = @Content(
                                    mediaType = "application/json")),
            }
    )
    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDetailDto> get(@PathVariable Long id) {
        HotelDetailDto hotelDetailDto = hotelService.getHotel(id);
        return ResponseEntity.ok(hotelDetailDto);
    }


    @Operation(
            summary = "Search hotel",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns summary view for found hotels",
                            content = @Content(
                                    mediaType = "application/json")),
            }
    )
    @GetMapping("/search")
    public ResponseEntity<List<HotelSummaryDto>> search(@ModelAttribute SearchHotelDto searchHotelDto) {
        List<HotelSummaryDto> hotelSummaryDtoList = hotelService.searchHotels(searchHotelDto);
        return ResponseEntity.ok(hotelSummaryDtoList);
    }


    @Operation(
            summary = "Get histogram",
            parameters = {@Parameter(name = "param", in = ParameterIn.PATH)},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns a map with counts grouped by the specified parameter",
                            content = @Content(
                                    mediaType = "application/json")),
            }
    )
    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHistogram(@PathVariable String param) {
        Map<String, Long> histogramMap = hotelService.getHistogram(param);
        return ResponseEntity.ok(histogramMap);
    }

    @Operation(
            summary = "Add amenities to the hotel",
            parameters = {@Parameter(name = "id", in = ParameterIn.PATH)},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "List of amenity names to add",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            type = "string"
                                    )
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Adds a list of amenities to the specified hotel by its id",
                            content = @Content(
                                    mediaType = "application/json")),
            }


    )
    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<Void> addAmenities(@PathVariable Long id, @RequestBody List<String> amenityNamesToAdd) {
        hotelService.addAmenities(id, amenityNamesToAdd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
