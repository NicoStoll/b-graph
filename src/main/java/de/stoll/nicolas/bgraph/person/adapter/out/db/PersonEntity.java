package de.stoll.nicolas.bgraph.person.adapter.out.db;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class PersonEntity {

    public PersonEntity(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Id
    private String personId;

    private String firstname;

    private String lastname;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        if (personId == null) {
            personId = "P-" + UUID.randomUUID();
        }
    }

    Person toPerson() {
        return new Person(this.personId, this.firstname, this.lastname);
    }

}
