package de.stoll.nicolas.bgraph.person.application.port.in.get;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import jakarta.validation.Valid;

import java.util.Optional;

public interface GetPersonByIdUseCase {

    Optional<Person> getPersonById(@Valid  GetPersonByIdQuery command);
}
