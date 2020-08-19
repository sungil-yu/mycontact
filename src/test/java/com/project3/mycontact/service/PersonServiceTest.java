package com.project3.mycontact.service;

import com.project3.mycontact.domain.Block;
import com.project3.mycontact.domain.Person;
import com.project3.mycontact.repository.BlockRepository;
import com.project3.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        givenBlocks();

        List<Person> result = personService.getPeopleExcludeBlocks();


        result.forEach(System.out::println);
    }

    private void givenBlocks() {
        givenBlock("martin");
    }

    private Block givenBlock(String name) {

        Block block = new Block();
        block.setName(name);

      return blockRepository.save(block);

    }

    private void givenBlockPerson(String name,int age,String bloodType){

        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(givenBlock(name));
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