package de.stoll.nicolas.bgraph.person.application.port.out;

public interface PersonCreatedEventPort {

    void publish(PersonCreatedEvent personCreatedEvent);
}
