package de.stoll.nicolas.bgraph.person.application.port.out;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetPersonPort {

    Page<Person> getAllPersons(Pageable pageable);
}
