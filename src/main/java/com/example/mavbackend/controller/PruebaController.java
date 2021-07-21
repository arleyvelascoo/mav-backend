package com.example.mavbackend.controller;


import com.example.mavbackend.dto.PruebaDTO;
import com.example.mavbackend.mapper.PruebaMapper;
import com.example.mavbackend.model.Prueba;
import com.example.mavbackend.service.interfac.IPruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for Prueba
 */

@RestController
@RequestMapping("/api/prueba")
public class PruebaController {
    private IPruebaService pruebaService;
    private PruebaMapper pruebaMapper;

    /**
     * Gets all registers from Prueba
     */
    @GetMapping("/all")
    public ResponseEntity<List<PruebaDTO>> getAll(){
        List<Prueba> pruebaList = this.pruebaService.getAll();
        if(pruebaList == null || pruebaList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(pruebaMapper.toPruebaDTOList(pruebaList), HttpStatus.OK);
    }

    @Autowired
    public void setPruebaService(@Qualifier("pruebaService") IPruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }

    @Autowired
    public void setPruebaMapper(PruebaMapper pruebaMapper) {
        this.pruebaMapper = pruebaMapper;
    }
}
