package com.project3.mycontact.controller;

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


    private MockMvc mvc;

    @Test
    void getPerson() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(personController).build();

        mvc.perform(get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void postPerson() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(personController).build();

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
        mvc = MockMvcBuilders.standaloneSetup(personController).build();
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
        mvc = MockMvcBuilders.standaloneSetup(personController).build();

        mvc.perform(patch("/api/person/1")
                .param("name" ,"martin22")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)

        )

                .andDo(print())
                .andExpect(status().isOk());
    }
}