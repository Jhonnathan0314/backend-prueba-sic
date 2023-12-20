package com.prueba.sic.pruebasic.context.person.application.usecase;

import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.domain.port.PersonRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonFindAllUseCase {

    private final PersonRepository personRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public List<Person> findAll() throws NoResultsException {
        List<Person> people = personRepository.findAll();
        if(people == null || people.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);
        return people;
    }

}
