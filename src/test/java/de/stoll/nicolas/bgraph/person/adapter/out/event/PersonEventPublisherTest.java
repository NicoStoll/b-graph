package de.stoll.nicolas.bgraph.person.adapter.out.event;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.port.out.events.PersonCreatedEvent;
import de.stoll.nicolas.bgraph.person.application.port.out.events.PersonDeletedEvent;
import de.stoll.nicolas.bgraph.person.application.port.out.events.PersonUpdatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.*;

class PersonEventPublisherTest {

    public static final String DUMMY_ID = "123";

    private ApplicationEventPublisher applicationEventPublisher;
    private PersonEventPublisher personEventPublisher;

    @BeforeEach
    void setUp() {
        applicationEventPublisher = mock(ApplicationEventPublisher.class);
        personEventPublisher = new PersonEventPublisher(applicationEventPublisher);
    }

    @Test
    void publishCreated_shouldPublishPersonCreatedEvent() {
        PersonCreatedEvent event = new PersonCreatedEvent(mock(Person.class));

        personEventPublisher.publishCreated(event);

        verify(applicationEventPublisher, times(1))
                .publishEvent(event);
    }

    @Test
    void publishUpdated_shouldPublishPersonUpdatedEvent() {
        PersonUpdatedEvent event = new PersonUpdatedEvent(mock(Person.class));

        personEventPublisher.publishUpdated(event);

        verify(applicationEventPublisher, times(1))
                .publishEvent(event);
    }

    @Test
    void publishDeleted_shouldPublishPersonDeletedEvent() {
        PersonDeletedEvent event = new PersonDeletedEvent(DUMMY_ID);

        personEventPublisher.publishDeleted(event);

        verify(applicationEventPublisher, times(1))
                .publishEvent(event);
    }
}