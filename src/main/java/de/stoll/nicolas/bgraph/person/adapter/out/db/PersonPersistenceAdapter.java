package de.stoll.nicolas.bgraph.person.adapter.out.db;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.domain.model.PersonQuery;
import de.stoll.nicolas.bgraph.person.application.port.out.CreatePersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.DeletePersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.GetPersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.UpdatePersonPort;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Log
@Component
@AllArgsConstructor
public class PersonPersistenceAdapter implements GetPersonPort, CreatePersonPort, UpdatePersonPort, DeletePersonPort {

    private PersonRepository personRepository;

    @Override
    public Person createPerson(Person person) {

        PersonEntity entity = new PersonEntity(person.getFirstName(), person.getLastName());

        this.personRepository.save(entity);

        log.info("Created person with id " + entity.getPersonId());

        return entity.toPerson();
    }

    @Override
    public Page<Person> getAllPersons(Pageable pageable) {

        return personRepository.findAll(pageable)
                .map(PersonEntity::toPerson);
    }

    @Override
    public void deletePersonById(String personId) {

        log.warning("NOT IMPLEMENTED");
    }

    @Override
    public Person updatePerson(Person updatedPerson) {

        PersonEntity entity = new PersonEntity(updatedPerson.getId(), updatedPerson.getFirstName(), updatedPerson.getLastName());

        personRepository.save(entity);

        return entity.toPerson();
    }
}
