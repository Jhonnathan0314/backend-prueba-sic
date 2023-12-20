package com.prueba.sic.pruebasic.context.person.application.usecase;


import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.domain.port.PersonRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonFindByIdUseCase {

    private final PersonRepository personRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public Person findById(Long id) throws NoResultsException {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);
        return optionalPerson.get();
    }

}
