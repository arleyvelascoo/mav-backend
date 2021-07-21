package com.example.mavbackend.service.interfac;

import com.example.mavbackend.model.Prueba;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface to prueba service
 */

@Service
public interface IPruebaService {

    public List<Prueba> getAll();
}
