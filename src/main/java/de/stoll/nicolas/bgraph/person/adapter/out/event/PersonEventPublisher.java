package de.stoll.nicolas.bgraph.person.adapter.out.event;

import de.stoll.nicolas.bgraph.person.application.port.out.events.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Log
@Component
@AllArgsConstructor
public class PersonEventPublisher implements PersonCreatedEventPort, PersonUpdatedEventPort, PersonDeletedEventPort {


    private final ApplicationEventPublisher publisher;

    @Override
    public void publishCreated(PersonCreatedEvent personCreatedEvent) {

        log.info("Publishing PersonCreatedEvent for person with id: " + personCreatedEvent.person().getId());

        this.publisher.publishEvent(personCreatedEvent);
    }

    @Override
    public void publishUpdated(PersonUpdatedEvent personUpdatedEvent) {

        log.info("Publishing PersonUpdatedEvent for person with id: " + personUpdatedEvent.updatedPerson().getId());

        this.publisher.publishEvent(personUpdatedEvent);
    }

    @Override
    public void publishDeleted(PersonDeletedEvent personDeletedEvent) {

        log.info("Publishing PersonDeletedEvent for person with id: " + personDeletedEvent.id());

        this.publisher.publishEvent(personDeletedEvent);
    }
}
