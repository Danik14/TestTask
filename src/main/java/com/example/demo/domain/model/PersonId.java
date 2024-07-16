package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;
@AllArgsConstructor
@Data
public class PersonId {
    @NonNull
    private final UUID id;

    @JsonCreator
    public static PersonId of(UUID id) {
        return new PersonId(id);
    }

    public static PersonId of(String id) {
        return new PersonId(UUID.fromString(id));
    }

    /**
     * This method is for testing purposes. Do not use it in the scope of domain.
     * <p>
     *
     * @return UUID representation of an ID-value
     */
    @JsonValue
    public UUID getAsUUID() {
        return getId();
    }

    /**
     * This method is for testing purposes. Do not use it in the scope of domain.
     * <p>
     *
     * @return string representation of an ID-value
     */
    public String getAsString() {
        return getId().toString();
    }
}
