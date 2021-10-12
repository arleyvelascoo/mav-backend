package com.example.mavbackend.service.implementation;

import com.example.mavbackend.model.City;
import com.example.mavbackend.repository.ICityRepository;
import com.example.mavbackend.service.interfac.ICityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Qualifier("principalCityService")
@AllArgsConstructor
@Service
public class CityServiceImpl implements ICityService {

    private final ICityRepository cityRepository;

    /**
     * Gets entire list of all states with pagination
     * @param pageable - Instance of Pageable
     */
    @Override
    public Page<City> getAll(Pageable pageable) {
        return this.cityRepository.findAll(pageable);
    }

    /**
     * Save a new city
     * @param city - Instance of City entity
     */
    @Override
    public City save(City city) {
        return this.cityRepository.save(city);
    }

    @Override
    public List<City> findByInput(String city) {
        return this.cityRepository.findByInput(city);
    }
}
