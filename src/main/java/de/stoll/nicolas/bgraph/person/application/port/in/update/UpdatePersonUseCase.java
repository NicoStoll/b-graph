package de.stoll.nicolas.bgraph.person.application.port.in.update;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

public interface UpdatePersonUseCase {

    Person updatePerson(UpdatePersonCommand updatePersonCommand);
}
