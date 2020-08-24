package com.project3.mycontact.service;

import com.project3.mycontact.controller.dto.PersonDto;
import com.project3.mycontact.domain.Person;
import com.project3.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PersonService {

    private PersonRepository personRepository;


    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeopleExcludeBlocks(){

//        List<Person> people = personRepository.findAll();
//        return people.stream().filter( person -> person.getBlock()==null).collect(Collectors.toList());

        return personRepository.findByBlockIsNull();

    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Person getPerson(Long id){
        Person person = personRepository.findById(id).orElse(null);

        log.info("person : {}",person);

        return person;
    }

    public List<Person> getPeopleByName(String name) {


        return personRepository.findByName(name);

    }

    @org.springframework.transaction.annotation.Transactional
    public void put(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {

        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        if(!person.getName().equals(personDto.getName())){
            new RuntimeException("이름이 다릅니다.");
        }

        person.set(personDto);


        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) {

        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setName(name);

        personRepository.save(person);
    }

    public void delete(Long id) {


        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setDeleted(true);
        personRepository.save(person);
    }

}
