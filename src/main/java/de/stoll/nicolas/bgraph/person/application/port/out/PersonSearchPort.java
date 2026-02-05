package de.stoll.nicolas.bgraph.person.application.port.out;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.domain.model.PersonCandidate;

import java.util.List;

public interface PersonSearchPort {

    List<PersonCandidate> searchPerson(Person person);
}
