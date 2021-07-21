package com.example.mavbackend.service.interfac;

import com.example.mavbackend.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service interface of State
 */

@Service
public interface IStateService {

    Page<State> getAll(Pageable pageable);

    State save(State state);
}
