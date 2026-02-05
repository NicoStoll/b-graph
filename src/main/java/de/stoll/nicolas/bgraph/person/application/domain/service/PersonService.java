package de.stoll.nicolas.bgraph.person.application.domain.service;

import de.stoll.nicolas.bgraph.person.application.port.in.CreatePersonCommand;
import de.stoll.nicolas.bgraph.person.application.port.in.CreatePersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.GetPersonUseCase;
import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements CreatePersonUseCase, GetPersonUseCase {

    @Override
    public Person createPerson(CreatePersonCommand command) {

        Person p = Person.builder()
                .id(-1L)
                .firstname(command.getPerson().firstname())
                .lastname(command.getPerson().lastname())
                .build();

        return p;
    }

    @Override
    public List<Person> getAllPersons() {
        return List.of();
    }
}
