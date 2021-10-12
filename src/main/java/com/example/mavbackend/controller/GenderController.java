package com.example.mavbackend.controller;

import com.example.mavbackend.dto.GenderDTO;
import com.example.mavbackend.mapper.GenderMapper;
import com.example.mavbackend.repository.IGenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gender")
public class GenderController {

    private GenderMapper genderMapper;
    private IGenderRepository genderRepository;

    @GetMapping("/all")
    public ResponseEntity<List<GenderDTO>> getAllGenders(){
        var response = this.genderRepository.findAll();
        if(response.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(genderMapper.toGenderDTOList(response));
    }


    @Autowired
    public void setGenderMapper(GenderMapper genderMapper) {
        this.genderMapper = genderMapper;
    }

    @Autowired
    public void setGenderRepository(IGenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }
}
