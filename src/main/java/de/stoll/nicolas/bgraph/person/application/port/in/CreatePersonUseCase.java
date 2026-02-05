package de.stoll.nicolas.bgraph.person.application.port.in;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

public interface CreatePersonUseCase {

    Person createPerson(CreatePersonCommand command);
}
