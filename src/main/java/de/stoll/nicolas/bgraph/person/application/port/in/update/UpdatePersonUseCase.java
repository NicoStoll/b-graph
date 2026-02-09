package de.stoll.nicolas.bgraph.person.application.port.in.update;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import jakarta.validation.Valid;

public interface UpdatePersonUseCase {

    Person updatePerson(@Valid UpdatePersonCommand updatePersonCommand);
}
