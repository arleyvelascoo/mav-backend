package com.example.mavbackend.controller;

import com.example.mavbackend.dto.CityDTO;
import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.mapper.CityMapper;
import com.example.mavbackend.service.interfac.ICityService;
import com.example.mavbackend.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/city")
public class CityController {
    private ICityService cityService;
    private CityMapper cityMapper;

    /**
     * Gets all registers of City with pagination and sorting
     *
     * @param pageable - Instance of Pageable
     */
    @GetMapping("/all")
    public ResponseEntity<Page<CityDTO>> getAll(@AuthenticationPrincipal UserDTO userDTO, Pageable pageable) {
        final var cityList = this.cityService.
                getAll(ITools.getPageRequest(pageable, CityDTO.getClavesToSort()));
        if (cityList == null || cityList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(cityList.map(c -> this.cityMapper.toCityDTO(c)), HttpStatus.OK);
    }

    /**
     * Save a new City
     *
     * @param cityDTO - Instance of CityDTO
     */
    @PostMapping
    public ResponseEntity<CityDTO> save(@Valid @RequestBody CityDTO cityDTO) {
        final var citySaved = this.cityService.save(cityMapper.toCity(cityDTO));
        if (citySaved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(cityMapper.toCityDTO(citySaved), HttpStatus.OK);
    }

    @Autowired
    public void setCityMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @Autowired
    public void setCityService(@Qualifier("principalCityService") ICityService cityService) {
        this.cityService = cityService;
    }
}
