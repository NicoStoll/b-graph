package de.stoll.nicolas.bgraph.person.application.domain.service;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.port.in.*;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonCommand;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonResult;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.create.PersonCreated;
import de.stoll.nicolas.bgraph.person.application.port.out.CreatePersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.DeletePersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.GetPersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.UpdatePersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.events.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@AllArgsConstructor
public class PersonService implements CreatePersonUseCase, GetPersonUseCase, GetPersonByIdUseCase, UpdatePersonUseCase, DeletePersonUseCase {

    private final GetPersonPort getPersonPort;

    private final PersonCreatedEventPort personCreatedEventPort;

    private final CreatePersonPort createPersonPort;

    private final PersonUpdatedEventPort personUpdatedEventPort;

    private final UpdatePersonPort updatePersonPort;

    private final PersonDeletedEventPort personDeletedEventPort;

    private final DeletePersonPort deletePersonPort;

    @Override
    @Transactional
    public CreatePersonResult createPerson(CreatePersonCommand command) {

        Person p = Person.builder()
                .id("-1")
                .firstname(command.getPerson().firstname())
                .lastname(command.getPerson().lastname())
                .build();

        Person created = this.createPersonPort.createPerson(p);

        this.personCreatedEventPort.publishCreated(new PersonCreatedEvent(created));

        return new PersonCreated(created);
    }

    @Override
    public List<Person> getAllPersons(GetPersonQuery query) {

        return this.getPersonPort.getAllPersons();
    }

    @Override
    public Person getPersonById(GetPersonByIdQuery command) {
        return null;
    }

    @Override
    public Person updatePerson(UpdatePersonCommand updatePersonCommand) {

        this.updatePersonPort.updatePerson(updatePersonCommand.getPerson());

        this.personUpdatedEventPort.publishUpdated(
                new PersonUpdatedEvent(updatePersonCommand.getPerson())
        );

        return null;
    }

    @Override
    public void deletePerson(DeletePersonCommand deletePersonCommand) {

        this.deletePersonPort.deletePersonById(deletePersonCommand.getId());

        this.personDeletedEventPort.publishDeleted(
                new PersonDeletedEvent(deletePersonCommand.getId())
        );


    }
}
