package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.RolDTO;
import com.example.mavbackend.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(target = "rolName", source = "name")
    RolDTO toRolDTO(Rol rol);

    List<RolDTO> toRolDTOList(List<Rol> rols);
}
