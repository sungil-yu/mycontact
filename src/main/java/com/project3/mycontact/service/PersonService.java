package com.project3.mycontact.service;

import com.project3.mycontact.controller.dto.PersonDto;
import com.project3.mycontact.domain.Person;
import com.project3.mycontact.exception.PersonNotFoundException;
import com.project3.mycontact.exception.RenameIsNotPermittedException;
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



    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }


    public List<Person> getPeopleByName(String name) {


        return personRepository.findByName(name);

    }

    @org.springframework.transaction.annotation.Transactional
    public void put(PersonDto personDto) {
        Person person = new Person();
        person.set(personDto);
        person.setName(personDto.getName());
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {

        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        if(!person.getName().equals(personDto.getName())){
            throw new RenameIsNotPermittedException();
        }

        person.set(personDto);


        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) {

        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        person.setName(name);

        personRepository.save(person);
    }

    public void delete(Long id) {


        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        person.setDeleted(true);
        personRepository.save(person);
    }

}
