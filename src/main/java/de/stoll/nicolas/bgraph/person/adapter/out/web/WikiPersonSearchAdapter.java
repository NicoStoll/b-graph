package de.stoll.nicolas.bgraph.person.adapter.out.web;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.domain.model.PersonCandidate;
import de.stoll.nicolas.bgraph.person.application.port.out.PersonSearchPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WikiPersonSearchAdapter implements PersonSearchPort {

    @Override
    public List<PersonCandidate> searchPerson(Person person) {
        return List.of();
    }
}
