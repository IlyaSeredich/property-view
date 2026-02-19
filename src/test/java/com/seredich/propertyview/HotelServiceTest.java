package com.seredich.propertyview;

import com.seredich.propertyview.dto.HotelCreateDto;
import com.seredich.propertyview.dto.HotelDetailDto;
import com.seredich.propertyview.dto.HotelSummaryDto;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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
    @DisplayName("Успешное создание отеля")
    void shouldCreateHotel() {
        HotelCreateDto createDto = HotelTestData.hotelCreateDto();
        Hotel hotelForSaving = HotelTestData.hotelForSaving();
        Hotel savedHotel = HotelTestData.savedHotel();
        HotelSummaryDto hotelSummaryDto = HotelTestData.hotelSummaryDto();

        when(hotelMapper.toEntity(createDto)).thenReturn(hotelForSaving);
        when(hotelRepository.save(hotelForSaving)).thenReturn(savedHotel);
        when(hotelMapper.toSummaryDto(savedHotel, "9 Pobediteley Avenue, Minsk, 220004, Belarus")).thenReturn(hotelSummaryDto);

        HotelSummaryDto result = hotelService.createHotel(createDto);

        assertNotNull(result);
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("DoubleTree by Hilton Minsk");
        assertThat(result.address()).isEqualTo("9 Pobediteley Avenue, Minsk, 220004, Belarus");
        assertThat(result.phone()).isEqualTo("+375 17 309-80-00");

        verify(hotelMapper).toEntity(createDto);
        verify(hotelRepository).save(hotelForSaving);
        verify(hotelMapper).toSummaryDto(savedHotel, "9 Pobediteley Avenue, Minsk, 220004, Belarus");
    }

    @Test
    @DisplayName("Успешное создание отеля")
    void shouldFindAllHotels() {
        Hotel savedHotel = HotelTestData.savedHotel();
        HotelSummaryDto hotelSummaryDto = HotelTestData.hotelSummaryDto();

        when(hotelRepository.findAll()).thenReturn(List.of(savedHotel));
        when(hotelMapper.toSummaryDto(savedHotel, "9 Pobediteley Avenue, Minsk, 220004, Belarus")).thenReturn(hotelSummaryDto);

        List<HotelSummaryDto> result = hotelService.getAllHotels();

        assertNotNull(result);
        assertThat(result.size()).isEqualTo(1);

        verify(hotelRepository).findAll();
        verify(hotelMapper).toSummaryDto(savedHotel, "9 Pobediteley Avenue, Minsk, 220004, Belarus");
    }

    @Test
    @DisplayName("Успешное создание отеля")
    void shouldGetHotelById() {
        Hotel savedHotel = HotelTestData.savedHotel();
        HotelDetailDto hotelDetailDto = HotelTestData.hotelDetailDto();

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(savedHotel));
        when(amenityService.getAmenityNameList(any())).thenReturn(List.of());
        when(hotelMapper.toDetailDto(savedHotel, List.of())).thenReturn(hotelDetailDto);

        HotelDetailDto result = hotelService.getHotel(1L);

        assertNotNull(result);
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("DoubleTree by Hilton Minsk");
        assertThat(result.brand()).isEqualTo("Hilton");

        verify(hotelRepository).findById(1L);
        verify(amenityService).getAmenityNameList(any());
        verify(hotelMapper).toDetailDto(savedHotel, List.of());
    }


}
