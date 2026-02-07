package de.stoll.nicolas.bgraph.temp;

import de.stoll.nicolas.bgraph.person.application.port.out.events.PersonCreatedEvent;
import de.stoll.nicolas.bgraph.person.application.port.out.events.PersonDeletedEvent;
import de.stoll.nicolas.bgraph.person.application.port.out.events.PersonUpdatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PersonEventListener {

    @EventListener
    void on(PersonCreatedEvent event) {
        System.out.println("Person created: Event published" + event.person());
    }

    @EventListener
    void on(PersonUpdatedEvent event) {
        System.out.println("Person updated: Event published" + event.updatedPerson());
    }

    @EventListener
    void on(PersonDeletedEvent event) {
        System.out.println("Person deleted: Event published with id " + event.id());
    }
}
