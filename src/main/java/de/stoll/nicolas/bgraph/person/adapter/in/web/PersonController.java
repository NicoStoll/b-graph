package de.stoll.nicolas.bgraph.person.adapter.in.web;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.port.in.get.GetPersonQuery;
import de.stoll.nicolas.bgraph.person.application.port.in.get.GetPersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.create.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log
@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor
@Tag(name = "Person API", description = "API for managing persons")
class PersonController {

    private final PersonModelMapper personModelMapper = new PersonModelMapper();

    private final GetPersonUseCase getPersonUseCase;

    private final CreatePersonUseCase createPersonUseCase;


    @Operation(
            summary = "Get all persons",
            description = "Retrieves a list of all persons."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Persons successfully retrieved",
            content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = PersonModel.class))
            )
    )
    @GetMapping("")
    public CollectionModel<EntityModel<PersonModel>> getAllPersons() {

        List<EntityModel<PersonModel>> result = this.getPersonUseCase.getAllPersons(new GetPersonQuery()).stream().map(person -> {

            PersonModel model = personModelMapper.toPersonModel(person);

            return EntityModel.of(model,
                    linkTo(methodOn(PersonController.class).getPersonById(model.getId())).withSelfRel()
            );
        }).toList();


        return CollectionModel.of(result,
                linkTo(methodOn(PersonController.class).getAllPersons()).withSelfRel()
        );
    }

    @Operation(
            summary = "Create a new person",
            description = "Creates a new person with the provided details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Person created successfully",
            content = @Content(schema = @Schema(implementation = PersonModel.class))
    )
    @PostMapping("")
    public ResponseEntity<EntityModel<PersonModel>> createPerson(@RequestBody CreatePersonDTO createPersonDTO) {

        Person temp = Person.builder()
                .firstname(createPersonDTO.firstName())
                .lastname(createPersonDTO.lastName())
                .build();

        CreatePersonCommand command = new CreatePersonCommand(temp);

        CreatePersonResult result = this.createPersonUseCase.createPerson(command);

        switch (result) {
            case PersonCreated pc -> {
                Person person = pc.person();
                return buildCreatedPersonResponse(person);
            }
            case PersonAmbiguous pa -> {
                // Handle ambiguity case if necessary
                throw new IllegalStateException("Person creation resulted in ambiguity: " + pa);
            }
            case PersonAlreadyExists pae -> {
                // Handle already exists case if necessary
                throw new IllegalStateException("Person already exists: " + pae);
            }
            // Handle other cases if necessary
            default -> throw new IllegalStateException("Unexpected value: " + result);
        }

    }

    private ResponseEntity<EntityModel<PersonModel>> buildCreatedPersonResponse(Person person) {

        PersonModel model = personModelMapper.toPersonModel(person);

        EntityModel<PersonModel> resource = EntityModel.of(
                model,
                linkTo(methodOn(PersonController.class).getPersonById(model.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).getAllPersons()).withRel("people")
        );

        return ResponseEntity.created(
                linkTo(methodOn(PersonController.class).getPersonById(model.getId())).toUri()
        ).body(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PersonModel>> getPersonById(@PathVariable String id) {
        return null;
    }
}
