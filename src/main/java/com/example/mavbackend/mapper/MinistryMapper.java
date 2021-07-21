package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.MinistryDTO;
import com.example.mavbackend.model.Ministry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper of Ministry
 */

@Mapper(componentModel = "spring")
public interface MinistryMapper {
    //Entity to DTO

    /**
     * Map Ministry to MinistryDTO
     *
     * @param ministry - Instance of Ministry
     */
    @Mapping(target = "secondLeaderName", source = "secondLeader.lastNameAndFirstName")
    @Mapping(target = "firstLeaderName", source = "firstLeader.lastNameAndFirstName")
    MinistryDTO toMinistryDTO(Ministry ministry);

    /**
     * Map List<Ministry> to List<MinistryDTO>
     *
     * @param ministryList - Instance of List<Ministry>
     */
    List<MinistryDTO> toMinistryDTOList(List<Ministry> ministryList);

    //DTO to Entity

    /**
     * Map MinistryDTO to Ministry
     * @param ministryDTO - Instance of MinistryDTO
     */
    @Mapping(target = "secondLeader", ignore = true)
    @Mapping(target = "higherMinistry", ignore = true)
    @Mapping(target = "firstLeader", ignore = true)
    Ministry toMinistry(MinistryDTO ministryDTO);
}
