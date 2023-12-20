package com.prueba.sic.pruebasic.context.person.application.usecase;

import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.domain.port.PersonRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.InvalidBodyException;
import com.prueba.sic.pruebasic.utils.exceptions.NoChangesException;
import com.prueba.sic.pruebasic.utils.exceptions.NoIdReceivedException;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonUpdateUseCase {

    private final ErrorMessages errorMessages = new ErrorMessages();
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public Person update(Long id, Person person) throws NoIdReceivedException, NoResultsException, NoChangesException, InvalidBodyException {
        person.setIdentificationNumber(id);
        if(person.getIdentificationNumber() == null) throw new NoIdReceivedException(errorMessages.NO_ID_RECEIVED);

        if(!person.isValid(person)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        Optional<Person> optPerson = personRepository.findById(person.getIdentificationNumber());
        if(optPerson.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);

        Person personDb = optPerson.get();
        if(areEquals(personDb, person)) throw new NoChangesException(errorMessages.NO_CHANGES);

        person.setCreationDate(personDb.getCreationDate());

        if(person.getPassword() != null) {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
        }

        person.setPassword(personDb.getPassword());

        return personRepository.update(person);
    }

    private boolean areEquals(Person oldPerson, Person newPerson) {
        return oldPerson.getName().equals(newPerson.getName()) &&
                oldPerson.getLastname().equals(newPerson.getLastname()) &&
                Objects.equals(oldPerson.getPhone(), newPerson.getPhone()) &&
                oldPerson.getAddress().equals(newPerson.getAddress()) &&
                oldPerson.getEmail().equals(newPerson.getEmail()) &&
                oldPerson.getPassword().equals(newPerson.getPassword());
    }

}
