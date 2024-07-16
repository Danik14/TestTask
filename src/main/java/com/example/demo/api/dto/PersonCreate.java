package com.example.demo.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
public class PersonCreate {

    @NotEmpty(message = "first name must not be empty")
    @Size(min = 1, max = 20, message = "first name's length should be between {min} and {max} letters")
    private String firstName;
    @NotEmpty(message = "last name must not be empty")
    @Size(min = 1, max = 20, message = "last name's length should be between {min} and {max} letters")
    private String lastName;
}
