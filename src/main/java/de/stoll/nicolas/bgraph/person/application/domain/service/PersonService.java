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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Log
@Service
@Validated
@AllArgsConstructor
public class PersonService implements CreatePersonUseCase, GetPersonUseCase, GetPersonByIdUseCase, UpdatePersonUseCase, DeletePersonUseCase {

    private final GetPersonPort getPersonPort;

    private final GetPersonByIdPort getPersonByIdPort;

    private final PersonSearchPort personSearchPort;

    private final PersonCreatedEventPort personCreatedEventPort;

    private final CreatePersonPort createPersonPort;

    private final PersonUpdatedEventPort personUpdatedEventPort;

    private final UpdatePersonPort updatePersonPort;

    private final PersonDeletedEventPort personDeletedEventPort;

    private final DeletePersonPort deletePersonPort;

    private final PersonMapper personMapper;

    @Override
    @Transactional
    public CreatePersonResult createPerson(CreatePersonCommand command) {

        Person p = this.personMapper.toPerson(command);

        List<PersonCandidate> candidates = this.personSearchPort.searchPerson(p);

        log.info("Found " + candidates.size() + " candidates for person " + p);

        Person created = this.createPersonPort.createPerson(p);

        this.personCreatedEventPort.publishCreated(new PersonCreatedEvent(created));

        return new PersonCreated(created);
    }

    @Override
    public Page<Person> getAllPersons(GetPersonQuery query) {

        Pageable pageable = Pageable.ofSize(query.getSize()).withPage(query.getPage());

        return this.getPersonPort.getAllPersons(query.getFirstName(), query.getLastName(), pageable);
    }

    @Override
    public Person getPersonById(GetPersonByIdQuery command) {

        return this.getPersonByIdPort
                .getSinglePersonById(command.getId())
                .orElseThrow(() -> new PersonNotFoundException(command.getId()));
    }

    @Override
    @Transactional
    public Person updatePerson(UpdatePersonCommand updatePersonCommand) {

        // TODO: Check if person exists
        this.getPersonByIdPort
                .getSinglePersonById(updatePersonCommand.getId())
                .orElseThrow(() -> new PersonNotFoundException(updatePersonCommand.getId()));

        Person updatedPerson = this.personMapper.toPerson(updatePersonCommand);

        log.info(updatedPerson.toString());

        Person result = this.updatePersonPort.updatePerson(updatedPerson);

        this.personUpdatedEventPort.publishUpdated(
                new PersonUpdatedEvent(result)
        );

        return result;
    }

    @Override
    @Transactional
    public void deletePerson(DeletePersonCommand deletePersonCommand) {

        Optional<Person> personToDelete = this.getPersonByIdPort
                .getSinglePersonById(deletePersonCommand.getId());

        if(personToDelete.isEmpty()) {
            return;
        }

        this.deletePersonPort.deletePersonById(personToDelete.get());

        this.personDeletedEventPort.publishDeleted(
                new PersonDeletedEvent(deletePersonCommand.getId())
        );


    }
}
