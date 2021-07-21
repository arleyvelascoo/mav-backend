package com.example.mavbackend.service.interfac;

import com.example.mavbackend.model.Ministry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service interface of Ministry
 */

@Service
public interface IMinistryService {

    Page<Ministry> getAll(Pageable pageable);

    Ministry save(Ministry ministry);
}
