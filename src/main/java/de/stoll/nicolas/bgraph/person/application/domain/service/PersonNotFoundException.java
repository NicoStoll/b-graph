package de.stoll.nicolas.bgraph.person.application.domain.service;

import lombok.AllArgsConstructor;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String personId) {
        super("Person with id '" + personId + "' not found");
    }
}
