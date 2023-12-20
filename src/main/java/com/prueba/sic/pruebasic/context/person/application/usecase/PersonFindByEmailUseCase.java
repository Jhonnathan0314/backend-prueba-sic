package com.prueba.sic.pruebasic.context.person.application.usecase;

import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.domain.port.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonFindByEmailUseCase {

    private final PersonRepository personRepository;

    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

}
