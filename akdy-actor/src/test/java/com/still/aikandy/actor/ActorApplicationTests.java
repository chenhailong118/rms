package com.still.aikandy.actor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.util.MultiValueMap;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
class ActorApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void queryActors() throws Exception {
        Integer pageNum = 1;
        Integer pageSize = 10;


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/actor")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        .param("id","1")
//        .param("name","Minami Hatsukawa")
//        .param("sex","0")
        .param("birthDateFrom","1995-01-01").param("birthDateTo","2000-01-01")
        .param("debutDateFrom","2010-01-01").param("debutDateTo","2020-01-01")
        )
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
        .andReturn();
        System.out.println(result.getResponse().getContentAsString(Charset.forName("UTF-8")));
    }

}
