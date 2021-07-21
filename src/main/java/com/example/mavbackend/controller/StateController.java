package com.example.mavbackend.controller;

import com.example.mavbackend.dto.StateDTO;
import com.example.mavbackend.mapper.StateMapper;
import com.example.mavbackend.service.interfac.IStateService;
import com.example.mavbackend.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller of State
 */

@RestController
@RequestMapping("/api/state")
public class StateController {
    private IStateService stateService;
    private StateMapper stateMapper;

    /**
     * Get all registers of State
     */
    @GetMapping("/all")
    public ResponseEntity<Page<StateDTO>> getAll(Pageable pageable){
        final var stateList = this.stateService.
                getAll(ITools.getPageRequest(pageable, StateDTO.getClavesToSort()));
        if(stateList == null || stateList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(stateList.map(x->this.stateMapper.toStateDTO(x)), HttpStatus.OK);
    }

    /**
     * Save a new State
     * @param stateDTO - Instance of StateDTO
     */
    @PostMapping
    public ResponseEntity<StateDTO> save(@Valid@RequestBody StateDTO stateDTO){
        final var stateSaved = this.stateService.save(stateMapper.toState(stateDTO));
        if(stateSaved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(stateMapper.toStateDTO(stateSaved), HttpStatus.OK);
    }

    @Autowired
    public void setStateService(@Qualifier("principalStateService") IStateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setStateMapper(StateMapper stateMapper) {
        this.stateMapper = stateMapper;
    }
}
