package com.example.mavbackend.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * General and util methods
 */

public interface ITools {

    /**
     * Gets a PageRequest with Pageable and a map to get Sort fields
     *
     * @param pageable     -
     * @param clavesToSort -
     */
    static Pageable getPageRequest(Pageable pageable, Map<String, String> clavesToSort) {
        var sort = Sort.unsorted();
        for (Sort.Order s : pageable.getSort())
            sort = sort.and(Sort.by(s.getDirection(), clavesToSort.get(s.getProperty())));

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

    }
}
