package de.stoll.nicolas.bgraph.person.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class PersonModel extends RepresentationModel<PersonModel> {

    private String id;

    private String firstName;

    private String lastName;
}
