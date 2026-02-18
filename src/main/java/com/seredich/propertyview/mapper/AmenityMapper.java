package com.seredich.propertyview.mapper;

import com.seredich.propertyview.entity.Amenity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AmenityMapper {

    @Mapping(target = "id", ignore = true)
    Amenity toEntity(String name);
}
