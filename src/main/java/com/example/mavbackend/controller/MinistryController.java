package com.example.mavbackend.controller;

import com.example.mavbackend.dto.MinistryDTO;
import com.example.mavbackend.mapper.MinistryMapper;
import com.example.mavbackend.service.interfac.IMinistryService;
import com.example.mavbackend.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller of Ministry
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/ministry")
public class MinistryController {
    private IMinistryService ministryService;
    private MinistryMapper ministryMapper;

    /**
     * Gets all registers of Ministry with pagination and sorting
     *
     * @param pageable - Instance of Pageable
     */
    @GetMapping("/all")
    public ResponseEntity<Page<MinistryDTO>> getAll(Pageable pageable) {
        final var ministryList = this.ministryService.
                getAll(ITools.getPageRequest(pageable, MinistryDTO.getClavesToSort()));
        if (ministryList == null || ministryList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(ministryList.map(m -> this.ministryMapper.toMinistryDTO(m)), HttpStatus.OK);
    }

    /**
     * Save a new Ministry
     *
     * @param ministryDTO - Instance of MinistryDTO
     */
    @PostMapping
    public ResponseEntity<MinistryDTO> save(@Valid @RequestBody MinistryDTO ministryDTO) {
        final var ministrySaved = this.ministryService.save(ministryMapper.toMinistry(ministryDTO));
        if (ministrySaved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(ministryMapper.toMinistryDTO(ministrySaved), HttpStatus.OK);
    }

    @Autowired
    public void setMinistryMapper(MinistryMapper ministryMapper) {
        this.ministryMapper = ministryMapper;
    }

    @Autowired
    public void setMinistryService(@Qualifier("principalMinistryService") IMinistryService ministryService) {
        this.ministryService = ministryService;
    }
}
