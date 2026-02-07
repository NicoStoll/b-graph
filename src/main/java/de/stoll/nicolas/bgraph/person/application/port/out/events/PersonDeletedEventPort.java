package de.stoll.nicolas.bgraph.person.application.port.out.events;

public interface PersonDeletedEventPort {

    void publishDeleted(PersonDeletedEvent personDeletedEvent);
}
