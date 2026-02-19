package com.seredich.propertyview;

import com.seredich.propertyview.dto.HotelCreateDto;
import com.seredich.propertyview.dto.HotelDetailDto;
import com.seredich.propertyview.dto.HotelSummaryDto;
import com.seredich.propertyview.dto.SearchHotelDto;
import com.seredich.propertyview.entity.Amenity;
import com.seredich.propertyview.entity.Hotel;
import com.seredich.propertyview.mapper.HotelMapper;
import com.seredich.propertyview.repository.HotelRepository;
import com.seredich.propertyview.service.AmenityService;
import com.seredich.propertyview.service.HotelService;
import com.seredich.propertyview.service.impl.HotelServiceImpl;
import com.seredich.propertyview.testdata.HotelTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                HotelServiceImpl.class
        }
)
public class HotelServiceTest {

    @MockitoBean
    private HotelRepository hotelRepository;

    @MockitoBean
    private HotelMapper hotelMapper;

    @MockitoBean
    private AmenityService amenityService;

    @Autowired
    HotelService hotelService;


    @Test
    @DisplayName("Should create hotel successfully")
    void shouldCreateHotel() {
        HotelCreateDto createDto = HotelTestData.hotelCreateDto();
        Hotel hotelForSaving = HotelTestData.hotelForSaving();
        Hotel savedHotel = HotelTestData.savedHotel();
        HotelSummaryDto hotelSummaryDto = HotelTestData.hotelSummaryDto();

        when(hotelMapper.toEntity(createDto)).thenReturn(hotelForSaving);
        when(hotelRepository.save(hotelForSaving)).thenReturn(savedHotel);
        when(hotelMapper.toSummaryDto(savedHotel, HotelTestData.DEFAULT_ADDRESS)).thenReturn(hotelSummaryDto);

        HotelSummaryDto result = hotelService.createHotel(createDto);

        assertNotNull(result);
        assertThat(result.id()).isEqualTo(HotelTestData.DEFAULT_ID);
        assertThat(result.name()).isEqualTo(HotelTestData.DEFAULT_NAME);
        assertThat(result.address()).isEqualTo(HotelTestData.DEFAULT_ADDRESS);
        assertThat(result.phone()).isEqualTo(HotelTestData.DEFAULT_PHONE);

        verify(hotelMapper).toEntity(createDto);
        verify(hotelRepository).save(hotelForSaving);
        verify(hotelMapper).toSummaryDto(savedHotel, HotelTestData.DEFAULT_ADDRESS);
    }

    @Test
    @DisplayName("Should return all hotels")
    void shouldFindAllHotels() {
        Hotel savedHotel = HotelTestData.savedHotel();
        HotelSummaryDto hotelSummaryDto = HotelTestData.hotelSummaryDto();

        when(hotelRepository.findAll()).thenReturn(List.of(savedHotel));
        when(hotelMapper.toSummaryDto(savedHotel, HotelTestData.DEFAULT_ADDRESS)).thenReturn(hotelSummaryDto);

        List<HotelSummaryDto> result = hotelService.getAllHotels();

        assertNotNull(result);
        assertThat(result.size()).isEqualTo(1);

        verify(hotelRepository).findAll();
        verify(hotelMapper).toSummaryDto(savedHotel, HotelTestData.DEFAULT_ADDRESS);
    }

    @Test
    @DisplayName("Should return hotel by ID with details")
    void shouldGetHotelById() {
        Hotel savedHotel = HotelTestData.savedHotel();
        HotelDetailDto hotelDetailDto = HotelTestData.hotelDetailDto();

        when(hotelRepository.findById(HotelTestData.DEFAULT_ID)).thenReturn(Optional.of(savedHotel));
        when(amenityService.getAmenityNameList(any())).thenReturn(List.of());
        when(hotelMapper.toDetailDto(savedHotel, List.of())).thenReturn(hotelDetailDto);

        HotelDetailDto result = hotelService.getHotel(HotelTestData.DEFAULT_ID);

        assertNotNull(result);
        assertThat(result.id()).isEqualTo(HotelTestData.DEFAULT_ID);
        assertThat(result.name()).isEqualTo(HotelTestData.DEFAULT_NAME);
        assertThat(result.brand()).isEqualTo(HotelTestData.DEFAULT_BRAND);

        verify(hotelRepository).findById(HotelTestData.DEFAULT_ID);
        verify(amenityService).getAmenityNameList(any());
        verify(hotelMapper).toDetailDto(savedHotel, List.of());
    }

    @Test
    @DisplayName("Should add amenities to existing hotel")
    void shouldAddAmenitiesToHotel() {
        Hotel savedHotel = HotelTestData.savedHotel();
        List<String> amenityNamesToAdd = List.of("Free parking", "Free WiFi");
        List<Amenity> preparedAmenities = HotelTestData.amenityList();

        when(hotelRepository.findById(HotelTestData.DEFAULT_ID)).thenReturn(Optional.of(savedHotel));
        when(amenityService.getPreparedAmenities(
                savedHotel.getAmenities(),
                amenityNamesToAdd
        ))
                .thenReturn(preparedAmenities);

        hotelService.addAmenities(HotelTestData.DEFAULT_ID, amenityNamesToAdd);

        verify(hotelRepository).findById(HotelTestData.DEFAULT_ID);
        verify(amenityService).getPreparedAmenities(savedHotel.getAmenities(), amenityNamesToAdd);
    }

    @Test
    @DisplayName("Should search hotels by name")
    void shouldSearchHotelByName() {
        SearchHotelDto searchHotelDto = SearchHotelDto.builder().name(HotelTestData.DEFAULT_NAME).build();
        Hotel savedHotel = HotelTestData.savedHotel();
        HotelSummaryDto hotelSummaryDto = HotelTestData.hotelSummaryDto();

        when(hotelRepository.findAll(any(Specification.class))).thenReturn(List.of(savedHotel));
        when(hotelMapper.toSummaryDto(savedHotel, HotelTestData.DEFAULT_ADDRESS))
                .thenReturn(hotelSummaryDto);

        List<HotelSummaryDto> result = hotelService.searchHotels(searchHotelDto);

        assertNotNull(result);
        assertThat(result.size()).isEqualTo(1);

        verify(hotelRepository).findAll(any(Specification.class));
        verify(hotelMapper).toSummaryDto(savedHotel, HotelTestData.DEFAULT_ADDRESS);
    }

    @Test
    @DisplayName("Should return histogram grouped by brand")
    void shouldGetHistogramByBrand() {
        Object[] objects = new Object[]{HotelTestData.DEFAULT_BRAND, HotelTestData.DEFAULT_ID};
        List<Object[]> brandCounts = new ArrayList<>();
        brandCounts.add(objects);

        when(hotelRepository.countByBrand()).thenReturn(brandCounts);

        Map<String, Long> result = hotelService.getHistogram("brand");

        assertNotNull(result);
        assertThat(result).containsKey(HotelTestData.DEFAULT_BRAND);
        assertThat(result).containsValue(HotelTestData.DEFAULT_ID);

        verify(hotelRepository).countByBrand();
    }
}
