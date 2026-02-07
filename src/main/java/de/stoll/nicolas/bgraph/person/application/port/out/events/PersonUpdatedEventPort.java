package de.stoll.nicolas.bgraph.person.application.port.out.events;

public interface PersonUpdatedEventPort {

    void publishUpdated(PersonUpdatedEvent personUpdatedEvent);
}
