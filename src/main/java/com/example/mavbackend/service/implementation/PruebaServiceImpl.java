package com.example.mavbackend.service.implementation;

import com.example.mavbackend.model.Prueba;
import com.example.mavbackend.repository.IPruebaRepository;
import com.example.mavbackend.service.interfac.IPruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for Prueba
 */

@Qualifier("pruebaService")
@Service
public class PruebaServiceImpl implements IPruebaService {

    private IPruebaRepository pruebaRepository;

    /**
     * Gets all registers of Prueba
     */
    @Override
    public List<Prueba> getAll() {
        return this.pruebaRepository.findAll();
    }

    @Autowired
    public void setPruebaRepository(IPruebaRepository pruebaRepository) {
        this.pruebaRepository = pruebaRepository;
    }
}
