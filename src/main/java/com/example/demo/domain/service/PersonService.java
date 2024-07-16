package com.example.demo.domain.service;

import com.example.demo.api.dto.PersonCreate;
import com.example.demo.db.entity.PersonEntity;
import com.example.demo.db.repository.PersonRepository;
import com.example.demo.domain.model.Person;
import com.example.demo.domain.model.PersonId;
import com.example.demo.mapper.PersonMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    public Person findPersonById(PersonId personId){
        return personMapper.toDomain(findPersonEntityById(personId));
    }

    public List<Person> findAllPeople(){
        return personMapper.toDomain(personRepository.findAll());
    }

    public Person createPerson(PersonCreate create){
        return personMapper.toDomain(
                personRepository.save(personMapper.toEntity(create))
        );
    }

    private PersonEntity findPersonEntityById(PersonId personId){
        return personRepository
                .findById(personId.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Person not found with id: " + personId)
                );
    }
}
