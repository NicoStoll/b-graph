package de.stoll.nicolas.bgraph.person.application.port.out.events;

public interface PersonCreatedEventPort {

    void publishCreated(PersonCreatedEvent personCreatedEvent);
}
