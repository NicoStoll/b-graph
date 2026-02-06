package de.stoll.nicolas.bgraph.person.adapter.out.event;

import de.stoll.nicolas.bgraph.person.application.port.out.PersonCreatedEvent;
import de.stoll.nicolas.bgraph.person.application.port.out.PersonCreatedEventPort;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonEventPublisher implements PersonCreatedEventPort {


    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(PersonCreatedEvent personCreatedEvent) {

        this.publisher.publishEvent(personCreatedEvent);
    }
}
