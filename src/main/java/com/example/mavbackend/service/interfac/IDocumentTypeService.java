package com.example.mavbackend.service.interfac;

import com.example.mavbackend.model.DocumentType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDocumentTypeService{


    List<DocumentType> findAll();
}
