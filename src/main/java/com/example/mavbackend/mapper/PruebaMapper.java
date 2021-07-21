package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.PruebaDTO;
import com.example.mavbackend.model.Prueba;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper of Prueba
 */

@Mapper(componentModel = "spring")
public interface PruebaMapper {

    /**
     * Mapper implementation of Prueba to PruebaDTO
     * @param prueba - Entity to be mapped
     */
    PruebaDTO toPruebaDTO(Prueba prueba);

    /**
     * Mapper implementation of List<Prueba> to List<PruebaDTO>
     * @param pruebaList - Entities List to be mapped
     */
    List<PruebaDTO> toPruebaDTOList(List<Prueba> pruebaList);
}
