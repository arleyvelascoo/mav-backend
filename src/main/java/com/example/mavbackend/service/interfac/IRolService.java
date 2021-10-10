package com.example.mavbackend.service.interfac;

import com.example.mavbackend.model.Rol;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRolService {
    List<Rol> getAll();

    Rol findById(Long idRol);
}
