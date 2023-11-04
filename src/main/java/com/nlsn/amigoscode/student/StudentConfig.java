package com.nlsn.amigoscode.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository
    ) {
        return args-> {
            Student mariam = new Student(
                    "Mariam",
                    LocalDate.of(2000,1,1),
                    "mariam.jamal@gmail.com"
            );

            Student alex = new Student(
                    "alex",
                    LocalDate.of(2000,1,1),
                    "alex@gmail.com"
            );

            studentRepository.saveAll(List.of(mariam, alex));
        };
    }
}
