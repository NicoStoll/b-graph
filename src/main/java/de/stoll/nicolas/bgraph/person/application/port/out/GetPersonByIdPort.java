package de.stoll.nicolas.bgraph.person.application.port.out;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

import java.util.Optional;

public interface GetPersonByIdPort {

    Optional<Person> getSinglePersonById(String id);
}
