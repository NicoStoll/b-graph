package de.stoll.nicolas.bgraph.person.application.port.in.delete;

import jakarta.validation.Valid;

public interface DeletePersonUseCase {

    void deletePerson(@Valid DeletePersonCommand deletePersonCommand);
}
