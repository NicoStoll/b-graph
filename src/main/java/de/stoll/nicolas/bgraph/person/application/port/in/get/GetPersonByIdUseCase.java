package de.stoll.nicolas.bgraph.person.application.port.in.get;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

public interface GetPersonByIdUseCase {

    Person getPersonById(GetPersonByIdQuery command);
}
