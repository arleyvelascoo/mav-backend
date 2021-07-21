package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.CityDTO;
import com.example.mavbackend.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper of City
 */

@Mapper(componentModel = "spring")
public interface CityMapper {
    //Entity to DTO

    /**
     * Map City to CityDTO
     * @param city - Instance of City
     */
    @Mapping(source = "state.name", target = "stateNamew")
    CityDTO toCityDTO(City city);

    /**
     * Map List<City> to List<CityDTO>
     * @param cityList - Instance of a List<City>
     */
    List<CityDTO> toCityDTOList(List<City> cityList);

    //DTO to Entity

    /**
     * Map CityDTO to City
     * @param cityDTO - Instance of CityDTO
     */
    @Mapping(target = "state", ignore = true)
    City toCity(CityDTO cityDTO);
}
