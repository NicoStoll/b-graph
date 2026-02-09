package de.stoll.nicolas.bgraph.person.application.port.in.get;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import org.springframework.data.domain.Page;

public interface GetPersonUseCase {

    Page<Person> getAllPersons(GetPersonQuery query);
}
