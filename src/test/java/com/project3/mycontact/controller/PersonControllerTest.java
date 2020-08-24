package com.project3.mycontact.controller;

import com.project3.mycontact.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @Autowired
    private PersonRepository personRepository;

    private MockMvc mvc;

    @BeforeEach
    void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getPerson() throws Exception {

        mvc.perform(get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void postPerson() throws Exception {

        mvc.perform(post("/api/person")
                .characterEncoding("utf-8")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"name\" : \"martin2\",\n" +
                "    \"age\":20,\n" +
                "    \"bloodType\":\"A\"}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    void modifyPerson() throws Exception {
        mvc.perform(put("/api/person/1")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\" : \"martin\",\n" +
                        "    \"age\":20,\n" +
                        "    \"bloodType\":\"A\"}"))

                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void modifyName() throws Exception {

        mvc.perform(patch("/api/person/1")
                .param("name" ,"martin22")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deletePerson() throws Exception {

        mvc.perform(delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());




    }
}