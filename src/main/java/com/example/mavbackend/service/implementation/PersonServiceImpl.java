package com.example.mavbackend.service.implementation;

import com.example.mavbackend.dto.UserDTO;
import com.example.mavbackend.exception.MAVValidationException;
import com.example.mavbackend.model.Ministry;
import com.example.mavbackend.model.Person;
import com.example.mavbackend.repository.*;
import com.example.mavbackend.service.interfac.IPersonService;
import com.example.mavbackend.util.IConstants;
import com.example.mavbackend.util.ITools;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Qualifier("principalPersonService")
@AllArgsConstructor
@Service
public class PersonServiceImpl implements IPersonService {

    private final IPersonRepository personRepository;
    private final EntityManager em;
    private final IDocumentTypeRepository documentTypeRepository;
    private final IGenderRepository genderRepository;
    private final IMinistryRepository ministryRepository;
    private final ICityRepository cityRepository;
    private final IRolRepository rolRepository;
    private final IUserRepository userRepository;
    private final IUserRolRepository userRolRepository;
    /**
     * Gets entire list of all persons with pagination
     * @param pageable - Instance of Pageable
     */
    @Override
    public Page<Person> getAll(Pageable pageable) {
        return this.personRepository.findAll(pageable);
    }


    @Override
    public Person findByIdUser(Long userId){
        var user = this.userRepository.findById(userId).orElseThrow(MAVValidationException::new);
        return this.personRepository.findTopByUserId(user.getId());
    }


    /**
     * Save a new Person
     * @param person - Instance of Person entity
     */
    @Override
    @Transactional
    public Person save(Person person,UserDTO userDTO) {

        var rol = this.rolRepository.findById(userDTO.getIdRol()).orElseThrow(MAVValidationException::new);
        if(!rol.getName().equals(IConstants.USERROL)){
            throw new MAVValidationException("Solo el líder o administrador puede agregar personas.");
        }
        var user = this.userRepository.findById(userDTO.getIdUser()).orElseThrow(MAVValidationException::new);
        var ministry = this.ministryRepository.findTopByIdUser(user.getId());
        validate(person, IConstants.INSERT_MODE);
        person.setIdMinistry(ministry.getId());
        this.personRepository.save(person);
        em.refresh(person);
        return person;
    }


    /**
     * Update a new Person
     * @param person - Instance of Person entity
     */
    @Override
    @Transactional
    public Person edit(Person person, UserDTO userDTO){
        var rol = this.rolRepository.findById(userDTO.getIdRol()).orElseThrow(MAVValidationException::new);
        if(Objects.equals(rol.getName(), IConstants.USERROL)){
            this.validateUser(person.getId(),userDTO.getIdUser());
        }else if(Objects.equals(rol.getName(), IConstants.LEADERROL)){
            this.validateLeader(person.getId(),userDTO.getIdUser());
        }
        var personBD = this.personRepository.findById(person.getId()).orElseThrow(MAVValidationException::new);
        person = (Person)ITools.copiarPropiedadesObjetoAHaciaBIgnorandoNulosDeA(person,personBD);
        validate(person, IConstants.EDIT_MODE);
        this.personRepository.save(person);
        this.setToResponse(person);
        return person;
    }

    /**
     * delete a new Person
     * @param personID - Instance of Person entity
     */
    @Override
    public void deleteById(Long personID, UserDTO userDTO) {

        var isUser = this.rolRepository.findById(userDTO.getIdRol()).orElseThrow(MAVValidationException::new);
        if(Objects.equals(isUser.getName(), IConstants.USERROL)){
            throw new MAVValidationException("No tiene permisos para deletear a alguien, y si no le sirve vaya busquelo a la casa.");
        }else if(Objects.equals(isUser.getName(), IConstants.LEADERROL)){
            this.validateLeader(personID,userDTO.getIdUser());
        }
        this.personRepository.deleteById(personID);
    }

    @Override
    public Person findById(Long personId) {
        return this.personRepository.findById(personId).orElseThrow(MAVValidationException::new);
    }

    @Override
    public List<Person> getAllDisciples(UserDTO userDTO) {
        var ministry = this.ministryRepository.findTopByIdUser(userDTO.getIdUser());
        return this.personRepository.findAllByIdMinistry(ministry.getId());
    }

    @Override
    public List<Ministry> getAllLeaders(UserDTO userDTO) {
        var ministry = this.ministryRepository.findTopByIdUser(userDTO.getIdUser());
        return this.ministryRepository.findAllByIdHigherMinistry(ministry.getId());
    }

    private void validate(Person person, String mode){
        var existing = this.personRepository
                .findTopByDocumentNumberAndIdDocumentType(person.getDocumentNumber(), person.getIdDocumentType());
        if (existing != null){
            if (IConstants.EDIT_MODE.equals(mode) && !existing.getId().equals(person.getId())){
                throw new MAVValidationException("Ya existe una persona con el mismo tipo y número de documento");
            }
            if (IConstants.INSERT_MODE.equals(mode)) {
                throw new MAVValidationException("Ya existe una persona con el mismo tipo y número de documento");
            }
        }
    }

    //private methods

    private void setToResponse(Person person) {
        if(person.getIdCity() != null){
            person.setCity(this.cityRepository.findById(person.getIdCity()).orElseThrow(MAVValidationException::new));
        }
        if(person.getIdDocumentType() != null){
            person.setDocumentType(this.documentTypeRepository.findById(person.getIdDocumentType()).orElseThrow(MAVValidationException::new));
        }
        if(person.getIdGender() != null){
            person.setGender(this.genderRepository.findById(person.getIdGender()).orElseThrow(MAVValidationException::new));
        }
        if(person.getIdMinistry() != null){
            person.setCity(this.cityRepository.findById(person.getIdCity()).orElseThrow(MAVValidationException::new));
        }
    }

    private void validateLeader(Long personId, Long idUser) {
        var pes = this.personRepository.findById(personId).orElseThrow(RuntimeException::new);
        var ministry = this.ministryRepository.findTopByIdUser(idUser);
        if(ministry == null) throw new MAVValidationException("are you a hacker? who give you the leader rol?");

        if(!pes.getIdMinistry().equals(ministry.getId())) throw new MAVValidationException("You are trying to edit a person that is not your disciple. it is not possible bro.");
    }

    private void validateUser(Long personId, Long idUser) {
        var pes = this.personRepository.findById(personId).orElseThrow(RuntimeException::new);
        if(!pes.getUserId().equals(idUser)) throw new MAVValidationException("Como va a modificar los datos de otro no sea perro.");
    }

}
