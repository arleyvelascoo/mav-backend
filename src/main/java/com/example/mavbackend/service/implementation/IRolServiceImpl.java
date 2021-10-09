package com.example.mavbackend.service.implementation;

import com.example.mavbackend.model.Rol;
import com.example.mavbackend.repository.IRolRepository;
import com.example.mavbackend.service.interfac.IRolService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IRolServiceImpl implements IRolService {

    private final IRolRepository rolRepository;

    @Override
    public List<Rol> getAll() {
        return this.rolRepository.findAll(Sort.by("name").ascending());
    }
}
