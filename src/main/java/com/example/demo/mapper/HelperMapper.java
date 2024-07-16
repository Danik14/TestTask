package com.example.demo.mapper;

import com.example.demo.domain.model.PersonId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HelperMapper {
    default PersonId toPersonId(UUID uuid){
        return PersonId.of(uuid);
    };

    default UUID toUUID(PersonId personId){
        return personId.getId();
    };
}
