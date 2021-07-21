package com.example.mavbackend.service.interfac;

import com.example.mavbackend.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service interface of City
 */

@Service
public interface ICityService {

    Page<City> getAll(Pageable pageable);

    City save(City city);
}
