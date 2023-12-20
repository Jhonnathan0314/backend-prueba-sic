package com.prueba.sic.pruebasic.context.person.application.usecase;

import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.domain.port.PersonRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.DuplicatedException;
import com.prueba.sic.pruebasic.utils.exceptions.InvalidBodyException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonCreateUseCase {

    private final PersonRepository personRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();
    private final PasswordEncoder passwordEncoder;

    public Person create(Person person) throws DuplicatedException, InvalidBodyException {

        if(person.getPassword() == null) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        person.setPassword(passwordEncoder.encode(person.getPassword()));

        if(!person.isValid(person)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        if(personRepository.findById(person.getIdentificationNumber()).isPresent()) throw new DuplicatedException(errorMessages.DUPLICATED);

        return personRepository.create(person);
    }

}
