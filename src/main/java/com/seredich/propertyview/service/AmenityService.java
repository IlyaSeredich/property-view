package com.seredich.propertyview.service;

import com.seredich.propertyview.entity.Amenity;

import java.util.List;

public interface AmenityService {
    List<Amenity> getPreparedAmenities(List<Amenity> hotelsAmenities, List<String> amenityNamesToAdd);

    List<String> getAmenityNameList(List<Amenity> amenities);
}
