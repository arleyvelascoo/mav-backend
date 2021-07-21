package com.example.mavbackend.service.implementation;

import com.example.mavbackend.model.State;
import com.example.mavbackend.repository.IStateRepository;
import com.example.mavbackend.service.interfac.IStateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service implementation of State
 */

@Qualifier("principalStateService")
@AllArgsConstructor
@Service
public class StateServiceImpl implements IStateService {

    private final IStateRepository stateRepository;

    /**
     * Get a entire list of all states
     */
    @Override
    public Page<State> getAll(Pageable pageable) {
        return this.stateRepository.findAll(pageable);
    }

    /**
     * Save a new state
     * @param state - Instance of State entity
     */
    @Override
    public State save(State state) {
        return this.stateRepository.save(state);
    }
}
