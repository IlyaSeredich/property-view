package com.seredich.propertyview.specification;

import com.seredich.propertyview.dto.SearchHotelDto;
import com.seredich.propertyview.entity.Amenity;
import com.seredich.propertyview.entity.Hotel;
import com.seredich.propertyview.service.AmenityService;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class HotelSpecification {
    private final AmenityService amenityService;

    public static Specification<Hotel> build(SearchHotelDto searchHotelDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            addNamePredicate(
                    searchHotelDto.name(),
                    root,
                    criteriaBuilder,
                    predicates
            );

            addBrandPredicate(
                    searchHotelDto.brand(),
                    root,
                    criteriaBuilder,
                    predicates
            );

            addCityPredicate(
                    searchHotelDto.city(),
                    root,
                    criteriaBuilder,
                    predicates
            );

            addCountryPredicate(
                    searchHotelDto.country(),
                    root,
                    criteriaBuilder,
                    predicates
            );

            addAmenitiesPredicate(
                    searchHotelDto.amenities(),
                    root,
                    query,
                    criteriaBuilder,
                    predicates
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }

    private static void addNamePredicate(
            String name,
            Root<Hotel> root,
            CriteriaBuilder criteriaBuilder,
            List<Predicate> predicates
    ) {
        if (name != null && !name.isBlank()) {
            String cleanName = name.trim().toLowerCase();
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("name")), cleanName
            ));
        }
    }

    private static void addBrandPredicate(
            String brand,
            Root<Hotel> root,
            CriteriaBuilder criteriaBuilder,
            List<Predicate> predicates
    ) {
        if (brand != null && !brand.isBlank()) {
            String cleanBrand = brand.trim().toLowerCase();
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("brand")), cleanBrand
            ));
        }
    }

    private static void addCityPredicate(
            String city,
            Root<Hotel> root,
            CriteriaBuilder criteriaBuilder,
            List<Predicate> predicates
    ) {
        if (city != null && !city.isBlank()) {
            String cleanCity = city.trim().toLowerCase();
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("address").get("city")), cleanCity
            ));
        }
    }

    private static void addCountryPredicate(
            String country,
            Root<Hotel> root,
            CriteriaBuilder criteriaBuilder,
            List<Predicate> predicates
    ) {
        if (country != null && !country.isBlank()) {
            String cleanCountry = country.trim().toLowerCase();
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("address").get("country")), cleanCountry
            ));
        }
    }

    private static void addAmenitiesPredicate(
            List<String> amenityNames,
            Root<Hotel> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder,
            List<Predicate> predicates
    ) {
        if (amenityNames != null && !amenityNames.isEmpty()) {

            Join<Hotel, Amenity> join = root.join("amenities");

            List<String> lowerAmenityNames = amenityNames.stream()
                    .map(amenityName -> amenityName.trim().toLowerCase())
                    .distinct()
                    .toList();

            predicates.add(
                    criteriaBuilder.lower(join.get("name")).in(lowerAmenityNames)
            );

            query.groupBy(root.get("id"));

            query.having(
                    criteriaBuilder.equal(
                            criteriaBuilder.countDistinct(join.get("id")),
                            amenityNames.size()
                    )
            );

            query.distinct(true);
        }
    }

}
