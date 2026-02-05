package de.stoll.nicolas.bgraph.person.application.port.in.create;

public interface CreatePersonUseCase {

    CreatePersonResult createPerson(CreatePersonCommand command);
}
