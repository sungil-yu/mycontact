package com.project3.mycontact.controller;


import com.project3.mycontact.controller.dto.PersonDto;
import com.project3.mycontact.domain.Person;
import com.project3.mycontact.repository.PersonRepository;
import com.project3.mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/api/person")
@RestController
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id){

        return personService.getPerson(id);
    }

    @GetMapping
    public Page<Person> getAll(@PageableDefault(page = 10,size = 10) Pageable pageable){
        return personService.getAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> postPerson(@RequestBody @Valid PersonDto personDto) throws URISyntaxException {

        personService.put(personDto);

        String uri = "/api/person";
        return ResponseEntity.created(new URI(uri)).body("{}");
    }


    //수정
    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id ,@RequestBody PersonDto personDto){
          personService.modify(id, personDto);

    }

    //일부 수정
    @PatchMapping("/{id}")
    public void modifyPerson(@PathVariable Long id ,String name){


        personService.modify(id,name);

    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.delete(id);
    }


}
