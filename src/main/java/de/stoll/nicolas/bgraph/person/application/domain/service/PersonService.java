package de.stoll.nicolas.bgraph.person.application.domain.service;

import de.stoll.nicolas.bgraph.person.application.port.in.*;
import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonCommand;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonResult;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.create.PersonCreated;
import de.stoll.nicolas.bgraph.person.application.port.out.CreatePersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.GetPersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.PersonCreatedEvent;
import de.stoll.nicolas.bgraph.person.application.port.out.PersonCreatedEventPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService implements CreatePersonUseCase, GetPersonUseCase {

    private final GetPersonPort getPersonPort;

    private final PersonCreatedEventPort personCreatedEventPort;

    private final CreatePersonPort createPersonPort;

    @Override
    @Transactional
    public CreatePersonResult createPerson(CreatePersonCommand command) {

        Person p = Person.builder()
                .id("-1")
                .firstname(command.getPerson().firstname())
                .lastname(command.getPerson().lastname())
                .build();

        Person created = this.createPersonPort.createPerson(p);

        this.personCreatedEventPort.publish(new PersonCreatedEvent(created));

        return new PersonCreated(created);
    }

    @Override
    public List<Person> getAllPersons() {

        return this.getPersonPort.getAllPersons();
    }
}
