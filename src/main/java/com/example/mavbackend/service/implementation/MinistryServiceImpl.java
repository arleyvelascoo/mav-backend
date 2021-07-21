package com.example.mavbackend.service.implementation;

import com.example.mavbackend.model.Ministry;
import com.example.mavbackend.repository.IMinistryRepository;
import com.example.mavbackend.service.interfac.IMinistryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Qualifier("principalMinistryService")
@AllArgsConstructor
@Service
public class MinistryServiceImpl implements IMinistryService {

    private final IMinistryRepository ministryRepository;

    /**
     * Gets entire list of all states with pagination
     * @param pageable - Instance of Pageable
     */
    @Override
    public Page<Ministry> getAll(Pageable pageable) {
        return this.ministryRepository.findAll(pageable);
    }

    /**
     * Save a new Ministry
     * @param ministry - Instance of Ministry entity
     */
    @Override
    public Ministry save(Ministry ministry) {
        return this.ministryRepository.save(ministry);
    }
}
