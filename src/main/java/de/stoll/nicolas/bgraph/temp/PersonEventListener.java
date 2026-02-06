package de.stoll.nicolas.bgraph.temp;

import de.stoll.nicolas.bgraph.person.application.port.out.PersonCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PersonEventListener {

    @EventListener
    void on(PersonCreatedEvent event) {
        System.out.println("Person created: Event published" + event.person());
    }
}
