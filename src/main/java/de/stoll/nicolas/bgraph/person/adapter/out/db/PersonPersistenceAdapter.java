package de.stoll.nicolas.bgraph.person.adapter.out.db;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.port.out.CreatePersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.DeletePersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.GetPersonPort;
import de.stoll.nicolas.bgraph.person.application.port.out.UpdatePersonPort;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.List;

@Log
@Component
@AllArgsConstructor
public class PersonPersistenceAdapter implements GetPersonPort, CreatePersonPort, UpdatePersonPort, DeletePersonPort {

    private PersonRepository personRepository;

    @Override
    public Person createPerson(Person person) {

        PersonEntity entity = new PersonEntity(person.firstname(), person.lastname());

        this.personRepository.save(entity);

        log.info("Created person with id " + entity.getPersonId());

        return entity.toPerson();
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll().stream().map(PersonEntity::toPerson).toList();
    }

    @Override
    public void deletePersonById(String personId) {

        log.warning("NOT IMPLEMENTED");
    }

    @Override
    public Person updatePerson(Person updatedPerson) {

        log.warning("NOT IMPLEMENTED");

        return null;
    }
}
