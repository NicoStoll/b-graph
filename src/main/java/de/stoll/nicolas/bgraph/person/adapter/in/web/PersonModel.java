package de.stoll.nicolas.bgraph.person.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class PersonModel extends RepresentationModel<PersonModel> {

    private long id;

    private String firstname;

    private String lastname;
}
