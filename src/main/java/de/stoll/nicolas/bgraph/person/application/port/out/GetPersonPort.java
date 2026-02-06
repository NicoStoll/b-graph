package de.stoll.nicolas.bgraph.person.application.port.out;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

import java.util.List;

public interface GetPersonPort {

    List<Person> getAllPersons();
}
