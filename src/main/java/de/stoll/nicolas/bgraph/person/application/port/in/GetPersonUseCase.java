package de.stoll.nicolas.bgraph.person.application.port.in;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

import java.util.List;

public interface GetPersonUseCase {

    List<Person> getAllPersons();
}
