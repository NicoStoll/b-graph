package de.stoll.nicolas.bgraph.person.application.port.in.get;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import jakarta.validation.Valid;

public interface GetPersonByIdUseCase {

    Person getPersonById(@Valid  GetPersonByIdQuery command);
}
