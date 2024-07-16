package com.example.demo.api.controller;

import com.example.demo.api.dto.PersonCreate;
import com.example.demo.api.dto.PersonResponse;
import com.example.demo.domain.model.PersonId;
import com.example.demo.domain.service.PersonService;
import com.example.demo.mapper.PersonMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
@Validated
public class PersonController {

    private final PersonService personService;

    private final PersonMapper personMapper;

    @GetMapping
    public List<PersonResponse> getAllPeople(){
        return personMapper.toResponse(personService.findAllPeople());
    };

    @GetMapping("{personId}")
    public PersonResponse getPersonById(@PathVariable @NotNull PersonId personId){
        return personMapper.toResponse(personService.findPersonById(personId));
    };

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse createPerson(@RequestBody @NotNull @Valid PersonCreate personCreate) {
        return personMapper.toResponse(personService.createPerson(personCreate));
    }
}
