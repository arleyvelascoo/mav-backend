package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.GenderDTO;
import com.example.mavbackend.model.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenderMapper {

    GenderDTO toGenderDTO(Gender gender);

    List<GenderDTO> toGenderDTOList(List<Gender> gender);

}
