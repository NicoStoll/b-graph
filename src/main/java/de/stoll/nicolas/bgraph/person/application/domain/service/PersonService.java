package de.stoll.nicolas.bgraph.person.application.domain.service;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.domain.model.PersonCandidate;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonCommand;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonResult;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.create.PersonCreated;
import de.stoll.nicolas.bgraph.person.application.port.in.delete.DeletePersonCommand;
import de.stoll.nicolas.bgraph.person.application.port.in.delete.DeletePersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.get.GetPersonByIdQuery;
import de.stoll.nicolas.bgraph.person.application.port.in.get.GetPersonByIdUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.get.GetPersonQuery;
import de.stoll.nicolas.bgraph.person.application.port.in.get.GetPersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.update.UpdatePersonCommand;
import de.stoll.nicolas.bgraph.person.application.port.in.update.UpdatePersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.out.*;
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

    private final PersonSearchPort personSearchPort;

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

        List<PersonCandidate> candidates = this.personSearchPort.searchPerson(p);

        log.info("Found " + candidates.size() + " candidates for person " + p);

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
