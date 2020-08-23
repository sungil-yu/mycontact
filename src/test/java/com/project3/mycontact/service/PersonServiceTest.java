package com.project3.mycontact.service;

import com.project3.mycontact.domain.Person;
import com.project3.mycontact.repository.BlockRepository;
import com.project3.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonService personService;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    BlockRepository blockRepository;


    @Test
    void getPeopleExcludeBlocks(){

        List<Person> result = personService.getPeopleExcludeBlocks();

        assertThat(result.size()).isEqualTo(3);

        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("david");
        assertThat(result.get(2).getName()).isEqualTo("benny");

    }




    @Test
    void getPerson(){

        Person person = personService.getPerson(3L);

        assertThat(person.getName()).isEqualTo("dennis");
    }

    @Test
    void getPeopleByName(){
        List<Person> result = personService.getPeopleByName("martin");

        assertThat(result.get(0).getName()).isEqualTo("martin");

    }


    @Test
    void findByBloodType(){

        personRepository.findByBloodType("A").forEach(System.out::println);
    }








}