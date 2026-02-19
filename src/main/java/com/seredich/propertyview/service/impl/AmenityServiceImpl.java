package com.seredich.propertyview.service.impl;

import com.seredich.propertyview.entity.Amenity;
import com.seredich.propertyview.mapper.AmenityMapper;
import com.seredich.propertyview.repository.AmenityRepository;
import com.seredich.propertyview.service.AmenityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AmenityServiceImpl implements AmenityService {
    private final AmenityRepository amenityRepository;
    private final AmenityMapper amenityMapper;

    @Override
    public List<Amenity> getPreparedAmenities(List<Amenity> hotelsAmenities, List<String> amenityNamesToAdd) {
      List<Amenity> amenities = addAmenities(amenityNamesToAdd);
      return amenities.stream().filter(amenity -> !hotelsAmenities.contains(amenity)).toList();
    }

    @Override
    public List<String> getAmenityNameList(List<Amenity> amenities) {
        return amenities.stream().map(Amenity::getName).toList();
    }

    private List<Amenity> addAmenities(List<String> amenitiesToAdd) {
        return amenitiesToAdd.stream().map(this::findOrCreateAmenity).toList();
    }

    private Amenity findOrCreateAmenity(String name) {
        return amenityRepository.findByNameIgnoreCase(name).orElseGet(() -> {
            Amenity amenity = amenityMapper.toEntity(name);
            return amenityRepository.save(amenity);
                }

        );
    }
}
