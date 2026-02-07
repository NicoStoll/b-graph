package de.stoll.nicolas.bgraph.person.application.port.in;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePersonCommand {

    String id;

    Person person;
}
