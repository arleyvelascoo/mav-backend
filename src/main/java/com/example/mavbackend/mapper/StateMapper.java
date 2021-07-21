package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.StateDTO;
import com.example.mavbackend.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper of State
 */

@Mapper(componentModel = "spring")
public interface StateMapper {
    //Entity to DTO

    /**
     * Map State to StateDTO
     * @param state - Instance of State
     */
    @Mapping(source = "country.name", target = "countryName")
    StateDTO toStateDTO(State state);

    /**
     * Map List<State> to List<StateDTO>
     * @param stateList - Instance of a List<State>
     */
    List<StateDTO> toStateDTOList(List<State> stateList);

    //DTO to Entity

    /**
     * Map StateDTO to State
     * @param stateDTO - Instance of StateDTO
     */
    @Mapping(target = "country", ignore = true)
    @Mapping(target = "capitalCity", ignore = true)
    State toState(StateDTO stateDTO);
}
