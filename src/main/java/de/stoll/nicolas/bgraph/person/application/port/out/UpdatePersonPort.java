package de.stoll.nicolas.bgraph.person.application.port.out;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

public interface UpdatePersonPort {

    Person updatePerson(Person updatedPerson);
}
