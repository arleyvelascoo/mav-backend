package com.example.mavbackend.mapper;

import com.example.mavbackend.dto.PersonDTO;
import com.example.mavbackend.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper of Person
 */

@Mapper(componentModel = "spring")
public interface PersonMapper {
    //Entity to DTO

    /**
     * Map Person to PersonDTO
     * @param person - Instance of Person
     */
    @Mapping(target = "genderName", source = "gender.name")
    @Mapping(target = "documentTypeName", source = "documentType.name")
    @Mapping(target = "cityName", source = "city.name")
    PersonDTO toPersonDTO(Person person);

    /**
     * Map List<Person> to List<PersonDTO>
     * @param personList - Instance of a List<Person>
     */
    List<PersonDTO> toPersonDTOList(List<Person> personList);

    //DTO to Entity

    /**
     * Map PersonDTO to Person
     * @param personDTO - Instance of PersonDTO
     */
    @Mapping(target = "lastNameAndFirstName", ignore = true)
    @Mapping(target = "ministry", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "city", ignore = true)
    Person toPerson(PersonDTO personDTO);
}
