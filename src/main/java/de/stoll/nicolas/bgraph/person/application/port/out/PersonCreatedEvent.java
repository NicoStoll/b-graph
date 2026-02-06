package de.stoll.nicolas.bgraph.person.application.port.out;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

public record PersonCreatedEvent(Person person) {
}
