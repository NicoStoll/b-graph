package de.stoll.nicolas.bgraph.person.adapter.in.web.response;

import de.stoll.nicolas.bgraph.person.adapter.in.web.PersonModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public class PersonAmbiguousCommand implements CreatePersonResponseCommand{

    @Override
    public ResponseEntity<EntityModel<PersonModel>> execute() {
        return null;
    }
}
