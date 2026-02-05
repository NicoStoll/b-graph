package de.stoll.nicolas.bgraph.person.adapter.out.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Person")
@AllArgsConstructor
@Data
public class PersonEntity {

    @Id
    private final String personId;

    private final String firstname;

    private final String lastname;
}
