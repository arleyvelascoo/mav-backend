package com.example.mavbackend.service.implementation;

import com.example.mavbackend.model.Country;
import com.example.mavbackend.repository.ICountryRepository;
import com.example.mavbackend.service.interfac.ICountryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation service of Country
 */

@Qualifier("principalCountryService")
@AllArgsConstructor
@Service
public class CountryServiceImpl implements ICountryService {

    private final ICountryRepository countryRepository;

    /**
     * Get a entire list of all countries
     */
    @Override
    public List<Country> getAll() {
        return this.countryRepository.findAll();
    }

    /**
     * Save a new country
     * @param country - Instance of Country entity
     */
    @Override
    public Country save(Country country) {
        return this.countryRepository.save(country);
    }
}
