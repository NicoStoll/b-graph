package de.stoll.nicolas.bgraph.person.adapter.out.db;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.port.out.CreatePersonPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonPersistenceAdapter implements CreatePersonPort {

    @Override
    public Person createPerson(Person person) {
        return null;
    }
}
