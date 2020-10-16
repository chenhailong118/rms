package com.still.rms.superstar;

import com.still.rms.common.dto.DictinfoDto;
import com.still.rms.common.querycondition.ActorQueryCondition;
import com.still.rms.mbg.mapper.ActorMapper;
import com.still.rms.mbg.mapper.RealtionActorResourceMapper;
import com.still.rms.mbg.mapper.ResourceMapper;
import com.still.rms.mbg.model.Dictinfo;
import com.still.rms.mbg.model.RealtionActorResource;
import com.still.rms.mbg.model.Resource;
import com.still.rms.superstar.dao.ActorCustomMapper;
import com.still.rms.superstar.dao.DictCustomMapper;
import com.still.rms.superstar.dao.RealtionActorResourceCustomMapper;
import com.still.rms.superstar.service.TestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class SuperstarApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ActorCustomMapper actorCustomMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private DictCustomMapper dictCustomMapper;
    @Autowired
    private RealtionActorResourceMapper realtionActorResourceMapper;

    private MockMvc mockMvc;

    @Autowired
    private TestServiceImpl testService;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void queryActors() throws Exception {

    }

    @Test
    public void test(){
        testService.test();
    }

}
