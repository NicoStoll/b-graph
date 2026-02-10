package de.stoll.nicolas.bgraph.person.adapter.in.web;

import de.stoll.nicolas.bgraph.person.adapter.in.web.response.CreatePersonResponseFactory;
import de.stoll.nicolas.bgraph.person.adapter.in.web.response.CreatePersonResponseCommand;
import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.port.in.create.*;
import de.stoll.nicolas.bgraph.person.application.port.in.delete.DeletePersonCommand;
import de.stoll.nicolas.bgraph.person.application.port.in.delete.DeletePersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.get.GetPersonByIdQuery;
import de.stoll.nicolas.bgraph.person.application.port.in.get.GetPersonByIdUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.get.GetPersonQuery;
import de.stoll.nicolas.bgraph.person.application.port.in.get.GetPersonUseCase;
import de.stoll.nicolas.bgraph.person.application.port.in.update.UpdatePersonCommand;
import de.stoll.nicolas.bgraph.person.application.port.in.update.UpdatePersonUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log
@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor
@Tag(name = "Person API", description = "API for managing persons")
public class PersonController {

    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 20;

    private final PersonModelMapper personModelMapper;

    private final GetPersonUseCase getPersonUseCase;

    private final CreatePersonUseCase createPersonUseCase;

    private final CreatePersonResponseFactory createPersonResponseBuilder;

    private final GetPersonByIdUseCase getPersonByIdUseCase;

    private final UpdatePersonUseCase updatePersonUseCase;

    private final DeletePersonUseCase deletePersonUseCase;

    //IntelliJ does not recognize the autowiring of this component
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Person> pagedResourcesAssembler;


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
    public PagedModel<EntityModel<PersonModel>> getAllPersons(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false, defaultValue = "") String firstname,
            @RequestParam(required = false, defaultValue = "") String lastname) {

        GetPersonQuery query = GetPersonQuery.builder()
                .page(page)
                .size(size)
                .firstName(firstname)
                .lastName(lastname)
                .build();

        Page<Person> result = this.getPersonUseCase.getAllPersons(query);

        return pagedResourcesAssembler.toModel(
                result,
                person -> {
                    PersonModel model = personModelMapper.toPersonModel(person);
                    return EntityModel.of(
                            model,
                            linkTo(methodOn(PersonController.class)
                                    .getPersonById(model.getId()))
                                    .withSelfRel()
                    );
                }
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

        CreatePersonCommand command = CreatePersonCommand.builder()
                .firstName(createPersonDTO.firstName())
                .lastName(createPersonDTO.lastName())
                .build();

        CreatePersonResult result = this.createPersonUseCase.createPerson(command);

        CreatePersonResponseCommand responseCommand = this.createPersonResponseBuilder.build(result);

        return responseCommand.execute();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PersonModel>> getPersonById(@PathVariable String id) {

        Person p = this.getPersonByIdUseCase.getPersonById(new GetPersonByIdQuery(id));

        PersonModel model = personModelMapper.toPersonModel(p);

        EntityModel<PersonModel> resource = EntityModel.of(
                model,
                linkTo(methodOn(PersonController.class)
                        .getPersonById(id))
                        .withSelfRel(),
                linkTo(methodOn(PersonController.class)
                        .getAllPersons(DEFAULT_PAGE, DEFAULT_SIZE, "", ""))
                        .withRel("people")
        );

        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PersonModel>> updatePerson(@PathVariable String id, @RequestBody UpdatePersonDTO updatePersonDTO) {

        if (!id.equals(updatePersonDTO.id())) {
            return ResponseEntity.badRequest().build();
        }

        UpdatePersonCommand command = UpdatePersonCommand.builder()
                .id(id)
                .firstName(updatePersonDTO.firstName())
                .lastName(updatePersonDTO.lastName())
                .build();

        Person p = this.updatePersonUseCase.updatePerson(command);

        PersonModel model = personModelMapper.toPersonModel(p);

        EntityModel<PersonModel> resource = EntityModel.of(model,
                linkTo(methodOn(PersonController.class).getPersonById(model.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).getAllPersons(DEFAULT_PAGE, DEFAULT_SIZE, "", "")).withRel("people")
        );

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id) {

        DeletePersonCommand command = new DeletePersonCommand(id);

        this.deletePersonUseCase.deletePerson(command);

        return ResponseEntity.noContent().build();
    }
}
