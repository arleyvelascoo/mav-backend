package com.example.mavbackend.controller;

import com.example.mavbackend.dto.CountryDTO;
import com.example.mavbackend.mapper.CountryMapper;
import com.example.mavbackend.model.Country;
import com.example.mavbackend.service.interfac.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Repository of Country
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final ICountryService countryService;
    private final CountryMapper countryMapper;

    @Autowired
    public CountryController(@Qualifier("principalCountryService") ICountryService countryService,
                             CountryMapper countryMapper){
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }

    /**
     * Get a entire list of all countries
     */
    @GetMapping("/all")
    public ResponseEntity<List<CountryDTO>> getAll(){
        var countryList = this.countryService.getAll();
        if(countryList == null || countryList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(this.countryMapper.toCountryDTOList(countryList), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CountryDTO> save(@Valid@RequestBody CountryDTO countryDTO){
        var countrySaved = this.countryService.save(countryMapper.toCountry(countryDTO));
        if(countrySaved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(countryMapper.toCountryDTO(countrySaved), HttpStatus.OK);
    }

}
