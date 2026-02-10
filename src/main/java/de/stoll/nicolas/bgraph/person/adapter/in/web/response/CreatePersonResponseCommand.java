package de.stoll.nicolas.bgraph.person.adapter.in.web.response;

import de.stoll.nicolas.bgraph.person.adapter.in.web.PersonModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface CreatePersonResponseCommand {

    ResponseEntity<EntityModel<PersonModel>> execute();
}
