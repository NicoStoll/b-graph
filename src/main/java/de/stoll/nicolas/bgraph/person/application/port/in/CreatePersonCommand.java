package de.stoll.nicolas.bgraph.person.application.port.in;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePersonCommand {

    private Person person;

}
