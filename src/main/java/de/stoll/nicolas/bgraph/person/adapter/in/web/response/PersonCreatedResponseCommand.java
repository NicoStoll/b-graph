package de.stoll.nicolas.bgraph.person.adapter.in.web.response;

import de.stoll.nicolas.bgraph.person.adapter.in.web.PersonController;
import de.stoll.nicolas.bgraph.person.adapter.in.web.PersonModel;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import static de.stoll.nicolas.bgraph.person.adapter.in.web.PersonController.DEFAULT_PAGE;
import static de.stoll.nicolas.bgraph.person.adapter.in.web.PersonController.DEFAULT_SIZE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
public class PersonCreatedResponseCommand implements CreatePersonResponseCommand{

    private final PersonModel model;


    @Override
    public ResponseEntity<EntityModel<PersonModel>> execute() {

        EntityModel<PersonModel> resource = EntityModel.of(
                model,
                linkTo(methodOn(PersonController.class)
                        .getPersonById(model.getId()))
                        .withSelfRel(),
                linkTo(methodOn(PersonController.class)
                        .getAllPersons(DEFAULT_PAGE, DEFAULT_SIZE, "", ""))
                        .withRel("people")
        );

        return ResponseEntity.created(
                linkTo(methodOn(PersonController.class)
                        .getPersonById(model.getId()))
                        .toUri()
        ).body(resource);
    }
}
