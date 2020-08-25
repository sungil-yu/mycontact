package com.project3.mycontact.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project3.mycontact.controller.dto.PersonDto;
import com.project3.mycontact.domain.Person;
import com.project3.mycontact.domain.dto.Birthday;
import com.project3.mycontact.exception.handler.GlobalExceptionHandler;
import com.project3.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Transactional
@SpringBootTest
class PersonControllerTest {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    void setUp(){
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(print())
                .build();
    }

    @Test
    void getPerson() throws Exception {

        mvc.perform(get("/api/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("martin"))
                .andExpect(jsonPath("$.hobby").isEmpty())
                .andExpect(jsonPath("$.address").isEmpty())
                .andExpect(jsonPath("$.birthday").value("1991-08-15"))
                .andExpect(jsonPath("$.job").isEmpty())
                .andExpect(jsonPath("$.phoneNumber").isEmpty())
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(jsonPath("$.age").isNumber())
                .andExpect(jsonPath("$.birthdayToday").isBoolean());

    }


    @Test
    void postPerson() throws Exception {

        PersonDto dto = PersonDto.of("martin", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");

        mvc.perform(post("/api/person")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andExpect(status().isCreated());

        Person result = personRepository.findAll(Sort.by(Sort.Direction.DESC,"id")).get(0);
        System.out.println("result" + result);
        assertAll(
                () ->assertThat(result.getName()).isEqualTo("martin"),
                () ->assertThat(result.getHobby()).isEqualTo("programming"),
                () -> assertThat(result.getAddress()).isEqualTo("판교"),
                () -> assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
                () -> assertThat(result.getJob()).isEqualTo("programmer"),
                () -> assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222")
        );
    }

    @Test
    void postPersonIfNameIsNull() throws Exception {
        PersonDto dto = new PersonDto();

        mvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름은 필수값입니다."))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postPersonIfNameIsEmpty() throws Exception {
        PersonDto dto = new PersonDto();

        dto.setName("");

        mvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름은 필수값입니다."))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postPersonIfNameBlankString() throws Exception {
        PersonDto dto = new PersonDto();

        dto.setName(" ");

        mvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름은 필수값입니다."));

    }
    @Test
    void modifyPerson() throws Exception {

        PersonDto dto = PersonDto.of("martin",  "programming","판교", LocalDate.now(), "programmer", "010-1111-2222");

        mvc.perform(put("/api/person/1")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andExpect(status().isOk());

        Person result = personRepository.findById(1L).get();
        assertAll(
                () ->assertThat(result.getHobby()).isEqualTo("programming"),
                () -> assertThat(result.getName()).isEqualTo("martin")
        );


    }

    @Test
    void modifyPersonIfNameIsDifferent() throws Exception {

        PersonDto dto = PersonDto.of("james",  "programming","판교", LocalDate.now(), "programmer", "010-1111-2222");

        mvc.perform(put("/api/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름 변경이 허용되지 않습니다."));

    }

    @Test
    void modifyPersonIfPersonNotFound() throws Exception {

        PersonDto dto = PersonDto.of("martin", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");

        mvc.perform(put("/api/person/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("Person의 Entity가 존재하지 않습니다."));


    }


    @Test
    void modifyName() throws Exception {

        mvc.perform(patch("/api/person/1")
                .param("name", "martinModified")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("martinModified");
    }

    @Disabled
    @Test
    void deletePerson() throws Exception {

        mvc.perform(delete("/api/person/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));


        assertTrue(personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals(1L)));

    }

    private String toJsonString(PersonDto personDto) throws JsonProcessingException {

        return objectMapper.writeValueAsString(personDto);
    }
}