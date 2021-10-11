package com.example.mavbackend.controller;

import com.example.mavbackend.dto.DocumentTypeDTO;
import com.example.mavbackend.mapper.DocumentTypeMapper;
import com.example.mavbackend.repository.IDocumentTypeRepository;
import com.example.mavbackend.service.interfac.IDocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/documentType")
public class DocumentTypeController {

    //idMinisterio no editar
    //endpoint autocompletar ciudad pais departamento.

    private IDocumentTypeService documentTypeService;
    private DocumentTypeMapper documentTypeMapper;

    @GetMapping("/all")
    public ResponseEntity<List<DocumentTypeDTO>> findAllDocumentType(){
        var documentList = this.documentTypeService.findAll();
        if(documentList == null || documentList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(documentTypeMapper.toDocumentTypeDTOList(documentList));
    }

    @Autowired
    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @Autowired
    public void setDocumentTypeMapper(DocumentTypeMapper documentTypeMapper) {
        this.documentTypeMapper = documentTypeMapper;
    }
}
