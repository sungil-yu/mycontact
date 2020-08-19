package com.project3.mycontact.service;

import com.project3.mycontact.domain.Block;
import com.project3.mycontact.domain.Person;
import com.project3.mycontact.repository.BlockRepository;
import com.project3.mycontact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {

    private PersonRepository personRepository;


    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeopleExcludeBlocks(){

        List<Person> people = personRepository.findAll();


        return people.stream().filter( person -> person.getBlock()==null).collect(Collectors.toList());

    }
}
