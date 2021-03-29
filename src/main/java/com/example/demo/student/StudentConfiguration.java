package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
              Student jack = new Student("Jack", "JackTheRipper@gmail.com", LocalDate.of(2000, Month.JANUARY, 21)) ;
              Student froppy = new Student("Froppy", "froppyTheFrog@gmail.com", LocalDate.of(2010, Month.FEBRUARY, 21)) ;
              repository.saveAll(List.of(jack, froppy));
        };
    }

}
