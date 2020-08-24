package com.project3.mycontact.repository;

import com.project3.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("John");

        personRepository.save(person);

        List<Person> persons = personRepository.findByName("John");

//        assertThat(persons.get(0).getAge()).isEqualTo(10);
        assertThat(persons.get(0).getName()).isEqualTo("John");


    }

    @Test
    void findByBirthdayBetween(){

        List<Person> result = personRepository.findByMonthOfBirthday(8);



        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("sophia");

    }






}