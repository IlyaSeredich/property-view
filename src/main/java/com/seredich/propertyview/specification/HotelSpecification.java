package com.seredich.propertyview.specification;

import com.seredich.propertyview.dto.SearchHotelDto;
import com.seredich.propertyview.entity.Hotel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecification {

    public static Specification<Hotel> build(SearchHotelDto searchHotelDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            addTitlePredicate(
                    searchHotelDto.name(),
                    root,
                    criteriaBuilder,
                    predicates
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }

    private static void addTitlePredicate(
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
}
