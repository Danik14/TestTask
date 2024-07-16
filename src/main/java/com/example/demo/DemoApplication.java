package com.example.demo;

import com.example.demo.db.entity.PersonEntity;
import com.example.demo.db.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {
	private final PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader() {
		return args -> {
            personRepository.save(
					PersonEntity.builder()
							.id(UUID.fromString("b1dc6a75-8d43-446e-984f-4af8a26d6003"))
							.firstName("Zharas")
							.lastName("ZharasLastName")
							.build()
			);
			personRepository.save(
					PersonEntity.builder()
							.id(UUID.fromString("66881aa2-cdaa-46a3-bfc7-0967ad4dec59"))
							.firstName("Daniyar")
							.lastName("Danchik")
							.build()
			);
			personRepository.save(
					PersonEntity.builder()
							.id(UUID.fromString("637e9a2d-892b-4c6f-8434-297512b49fe0"))
							.firstName("Bogdan")
							.lastName("Marzoev")
							.build()
			);
        };
    }
}
