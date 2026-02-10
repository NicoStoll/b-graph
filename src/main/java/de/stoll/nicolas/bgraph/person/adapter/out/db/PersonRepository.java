package de.stoll.nicolas.bgraph.person.adapter.out.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<PersonEntity, String> {

    @Query("""
        SELECT p FROM PersonEntity p
        WHERE (:firstname = '' OR LOWER(p.firstname) LIKE LOWER(CONCAT('%', :firstname, '%')))
          AND (:lastname = '' OR LOWER(p.lastname) LIKE LOWER(CONCAT('%', :lastname, '%')))
    """)
    Page<PersonEntity> search(
            @Param("firstname") String firstname,
            @Param("lastname") String lastname,
            Pageable pageable
    );
}
