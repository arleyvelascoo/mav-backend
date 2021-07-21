package com.example.mavbackend.service.interfac;

import com.example.mavbackend.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface of Country
 */

@Service
public interface ICountryService {

    List<Country> getAll();

    Country save(Country country);
}
