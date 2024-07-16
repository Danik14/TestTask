package com.example.demo.mapper;

import com.example.demo.api.dto.PersonCreate;
import com.example.demo.api.dto.PersonResponse;
import com.example.demo.db.entity.PersonEntity;
import com.example.demo.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(uses = HelperMapper.class, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {

    PersonResponse toResponse(Person person);

    List<PersonResponse> toResponse(List<Person> person);

    Person toDomain(PersonEntity entity);

    List<Person> toDomain(List<PersonEntity> entity);

    @Mapping(target = "id", ignore = true)
    PersonEntity toEntity(PersonCreate create);

    PersonEntity toEntity(Person person);
}
