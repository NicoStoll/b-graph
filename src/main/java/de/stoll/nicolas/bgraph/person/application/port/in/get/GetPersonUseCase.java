package de.stoll.nicolas.bgraph.person.application.port.in.get;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface GetPersonUseCase {

    Page<Person> getAllPersons(@Valid GetPersonQuery query);
}
