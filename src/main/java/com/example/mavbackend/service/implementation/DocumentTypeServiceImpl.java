package com.example.mavbackend.service.implementation;

import com.example.mavbackend.model.DocumentType;
import com.example.mavbackend.repository.IDocumentTypeRepository;
import com.example.mavbackend.service.interfac.IDocumentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DocumentTypeServiceImpl implements IDocumentTypeService {

    private IDocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentType> findAll() {
        return this.documentTypeRepository.findAll();
    }
}
