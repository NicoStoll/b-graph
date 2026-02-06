package de.stoll.nicolas.bgraph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BGraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(BGraphApplication.class, args);
    }

}
