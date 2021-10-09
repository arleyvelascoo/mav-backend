package com.example.mavbackend.service.implementation;

import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.exception.MAVValidationException;
import com.example.mavbackend.model.Ministry;
import com.example.mavbackend.repository.IMinistryRepository;
import com.example.mavbackend.repository.IPersonRepository;
import com.example.mavbackend.service.interfac.IMinistryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Qualifier("principalMinistryService")
@AllArgsConstructor
@Service
public class MinistryServiceImpl implements IMinistryService {

    private final IMinistryRepository ministryRepository;
    private final IPersonRepository personRepository;
    private final EntityManager em;

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
    @Transactional
    public Ministry save(Ministry ministry) {
        return this.ministryRepository.save(ministry);
    }

    @Override
    @Transactional
    public Ministry registerPerson(UserDTO user , Ministry ministry)  {
        var firstLeader = ministry.getIdFirstLeader();
        var secondLeader = ministry.getIdSecondLeader();
        var higherMinistry = this.ministryRepository.findTopByIdUser(user.getIdUser());
        if(higherMinistry == null){
            throw new MAVValidationException("Autorización requerida para acceder a la funcionalidad.");
        }
        if(firstLeader == null && secondLeader == null){
            throw  new MAVValidationException("No puede ser registrado un ministerio sin líderes.");
        }
        if(firstLeader!=null){
            var firstPerson = this.personRepository.findById(firstLeader).orElseThrow(MAVValidationException::new);
            var exists = this.ministryRepository.findByIdPerson(firstPerson.getId());
            if(exists != null) throw new MAVValidationException("No sea tonto pa, ya está registrado reykon el líder.");
        }
        if(secondLeader!=null){
            var secondPerson = this.personRepository.findById(secondLeader).orElseThrow(MAVValidationException::new);
            var exists = this.ministryRepository.findByIdPerson(secondPerson.getId());
            if(exists != null) throw new MAVValidationException("No sea tonto pa, ya está registrado reykon el líder 2 cambio.");
        }
        ministry.setIdMinistryType(higherMinistry.getId());
        this.ministryRepository.save(ministry);
        this.em.refresh(ministry);
        return ministry;
    }


}
