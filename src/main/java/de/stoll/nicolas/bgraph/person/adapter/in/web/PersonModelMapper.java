package de.stoll.nicolas.bgraph.person.adapter.in.web;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

public class PersonModelMapper {

    PersonModel toPersonModel(Person person) {
        return new PersonModel(
                person.id(),
                person.firstname(),
                person.lastname()
        );
    }
}
