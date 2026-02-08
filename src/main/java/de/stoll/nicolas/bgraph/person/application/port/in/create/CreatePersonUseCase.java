package de.stoll.nicolas.bgraph.person.application.port.in.create;

import jakarta.validation.Valid;

public interface CreatePersonUseCase {

    CreatePersonResult createPerson(@Valid CreatePersonCommand command);
}
