package com.prueba.sic.pruebasic.context.person.infrastructure.adapter;

import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.domain.port.PersonRepository;
import com.prueba.sic.pruebasic.context.person.infrastructure.mappers.PersonMapper;
import com.prueba.sic.pruebasic.context.person.infrastructure.persistence.PersonEntity;
import com.prueba.sic.pruebasic.context.person.infrastructure.persistence.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryJpaAdapter implements PersonRepository {

    private final PersonJpaRepository personJpaRepository;
    private final PersonMapper mapper = new PersonMapper();

    @Override
    public List<Person> findAll() {
        List<PersonEntity> personEntities = personJpaRepository.findAll();
        return mapper.entitiesToModels(personEntities);
    }

    @Override
    public Optional<Person> findById(Long id) {
        Optional<PersonEntity> optPersonEntity = personJpaRepository.findById(id);
        return optPersonEntity.map(mapper::entityToModel);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        Optional<PersonEntity> optPersonEntity = personJpaRepository.findByEmail(email);
        return optPersonEntity.map(mapper::entityToModel);
    }

    @Override
    public Person create(Person person) {
        PersonEntity personEntity = personJpaRepository.save(mapper.modelToEntity(person));
        return mapper.entityToModel(personEntity);
    }

    @Override
    public Person update(Person person) {
        PersonEntity personEntity = personJpaRepository.save(mapper.modelToEntity(person));
        return mapper.entityToModel(personEntity);
    }

    @Override
    public void deleteById(Long id) {
        personJpaRepository.deleteById(id);
    }

}
