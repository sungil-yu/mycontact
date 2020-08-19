package com.project3.mycontact.repository;

import com.project3.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("John");
        person.setAge(10);
        person.setBloodType("A");

        personRepository.save(person);
        System.out.println(person);
        List<Person> persons = personRepository.findAll();

        assertThat(persons.get(0).getAge()).isEqualTo(20);
        assertThat(persons.get(0).getName()).isEqualTo("John");


    }

}