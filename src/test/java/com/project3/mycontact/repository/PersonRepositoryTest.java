package com.project3.mycontact.repository;

import com.project3.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void findByBirthdayBetween(){
    givenPeople();

        List<Person> result = personRepository.findByBirthdayBetween(
                LocalDate.of(1991, 8, 1),
                LocalDate.of(1991, 8, 31)
        );

        result.forEach(System.out::println);

    }

    private void givenPeople() {
        givenPerson("martin", 10, "A",LocalDate.of(1991,8,3));
        givenPerson("david", 12, "B",LocalDate.of(1992,3,4));
        givenPerson("dennis", 40, "O",LocalDate.of(1991,8,14));
        givenPerson("sophia", 10, "O",LocalDate.of(1992,6,14));
        givenPerson("ann", 20, "AB",LocalDate.of(1991,8,24));
    }
    private void givenPerson(String name, int age, String bloodType,LocalDate birthday) {


        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setBloodType(bloodType);
        person.setBirthday(birthday);

        personRepository.save(person);
    }
}