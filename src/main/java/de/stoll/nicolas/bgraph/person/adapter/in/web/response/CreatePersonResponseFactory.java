package de.stoll.nicolas.bgraph.person.adapter.in.web.response;

import de.stoll.nicolas.bgraph.person.adapter.in.web.PersonModel;
import de.stoll.nicolas.bgraph.person.adapter.in.web.PersonModelMapper;
import de.stoll.nicolas.bgraph.person.application.port.in.create.CreatePersonResult;
import de.stoll.nicolas.bgraph.person.application.port.in.create.PersonAlreadyExists;
import de.stoll.nicolas.bgraph.person.application.port.in.create.PersonAmbiguous;
import de.stoll.nicolas.bgraph.person.application.port.in.create.PersonCreated;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatePersonResponseFactory {

    private final PersonModelMapper personModelMapper;

    public CreatePersonResponseCommand build(CreatePersonResult result) {
        switch (result) {
            case PersonAlreadyExists personAlreadyExists -> {
                return new PersonAlreadyExistsCommand();
            }

            case PersonAmbiguous personAmbiguous -> {
                return new PersonAmbiguousCommand();
            }

            case PersonCreated personCreated -> {
                PersonModel model = this.personModelMapper.toPersonModel(personCreated.person());
                return new PersonCreatedResponseCommand(model);
            }
        }
    }
}
