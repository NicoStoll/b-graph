package de.stoll.nicolas.bgraph.person.application.domain.service;

import de.stoll.nicolas.bgraph.person.application.port.in.*;
import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonCommand;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonResult;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.create.PersonCreated;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements CreatePersonUseCase, GetPersonUseCase {

    @Override
    public CreatePersonResult createPerson(CreatePersonCommand command) {

        Person p = Person.builder()
                .id(-1L)
                .firstname(command.getPerson().firstname())
                .lastname(command.getPerson().lastname())
                .build();

        return new PersonCreated(p);
    }

    @Override
    public List<Person> getAllPersons() {
        return List.of();
    }
}
