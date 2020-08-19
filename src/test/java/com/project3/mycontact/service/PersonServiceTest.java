package com.project3.mycontact.service;

import com.project3.mycontact.domain.Block;
import com.project3.mycontact.domain.Person;
import com.project3.mycontact.repository.BlockRepository;
import com.project3.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        givenPeople();

        List<Person> result = personService.getPeopleExcludeBlocks();


        result.forEach(System.out::println);
    }


    @Test
    void cascadeTest(){
        givenPeople();
        List<Person> result = personRepository.findAll();

        result.forEach(System.out::println);

        Person person = result.stream().filter(people -> people.getBlock()!=null).collect(Collectors.toList()).get(0);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());

        personRepository.save(person);

        personRepository.findAll().forEach(System.out::println);

        personRepository.delete(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);


    }

    private void givenBlockPerson(String name,int age,String bloodType){

        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(new Block(name));
        personRepository.save(blockPerson);

    }

    private void givenPeople() {
        givenPerson("martin", 10, "A");
        givenBlockPerson("martin", 30, "A");
        givenPerson("david", 12, "B");
        givenPerson("dennis", 40, "O");
        givenPerson("ann", 20, "AB");
    }

    private void givenPerson(String name, int age, String bloodType) {


        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setBloodType(bloodType);


        personRepository.save(person);
    }


}