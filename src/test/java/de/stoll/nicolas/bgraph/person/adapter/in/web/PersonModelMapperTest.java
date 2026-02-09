package de.stoll.nicolas.bgraph.person.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import de.stoll.nicolas.bgraph.person.application.domain.model.Person;

import static org.assertj.core.api.Assertions.assertThat;

class PersonModelMapperTest {

    private final PersonModelMapper mapper =
            Mappers.getMapper(PersonModelMapper.class);

    @Test
    void shouldMapPersonToPersonModel() {
        // given
        Person person = new Person(
                "P-123",
                "Friedrich",
                "Merz"
        );

        // when
        PersonModel model = mapper.toPersonModel(person);

        // then
        assertThat(model).isNotNull();
        assertThat(model.getId()).isEqualTo("P-123");
        assertThat(model.getFirstName()).isEqualTo("Friedrich");
        assertThat(model.getLastName()).isEqualTo("Merz");
    }
}