package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.DocumentTypeDTO;
import com.example.mavbackend.model.DocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {


    @Mapping(target = "documentName", expression =  "java(documentType.getAcronym() " + "+" + "\" - \"" + "+" + "documentType.getName())")
    DocumentTypeDTO toDocumentTypeDTO(DocumentType documentType);


    List<DocumentTypeDTO> toDocumentTypeDTOList(List<DocumentType> documentList);
}
