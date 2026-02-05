package de.stoll.nicolas.bgraph.person.application.port.in.create;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

public record PersonAlreadyExists(Person existingPerson) implements CreatePersonResult {
}
