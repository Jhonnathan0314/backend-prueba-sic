package com.prueba.sic.pruebasic.context.person.domain.port;

import com.prueba.sic.pruebasic.context.person.domain.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    List<Person> findAll();
    Optional<Person> findById(Long id);
    Optional<Person> findByEmail(String email);
    Person create(Person person);
    Person update(Person person);
    void deleteById(Long id);

}
