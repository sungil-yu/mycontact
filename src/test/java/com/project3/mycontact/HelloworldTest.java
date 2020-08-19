package com.project3.mycontact;

import com.project3.mycontact.controller.HelloworldController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class HelloWorldTest {

    @Autowired
    private HelloworldController helloworldController;

    private MockMvc mvc;

    @Test
    void hello(){

    }

    @Test
    void mockMvc() throws Exception {

        mvc = MockMvcBuilders.standaloneSetup(helloworldController).build();


        mvc.perform(
                MockMvcRequestBuilders.get("/api/hello")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hi"));
    }
}