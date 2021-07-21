package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.CountryDTO;
import com.example.mavbackend.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper of Country
 */

@Mapper(componentModel = "spring")
public interface CountryMapper {

    // Entity to DTO

    /**
     * Map Country instance to a new instance of CountryDTO
     * @param country - Instance of Country entity
     */
    CountryDTO toCountryDTO(Country country);

    /**
     * Map List<Country> instance to a new instance of List<CountryDTO>
     * @param countryList - Instance of List<Country>
     */
    List<CountryDTO> toCountryDTOList(List<Country> countryList);

    // DTO to Entity

    /**
     * Map CountryDTO to Country
     * @param countryDTO - Instance of CountryDTO
     */
    Country toCountry(CountryDTO countryDTO);
}
