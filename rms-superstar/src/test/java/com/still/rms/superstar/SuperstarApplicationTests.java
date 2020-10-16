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

    @Test
    public void updateResources(){
        int count = 0;

        String updateDir = "G:\\update";
        String country = "日本";
        String basePath = "/resource2";
        Integer mark = 2;
        Integer theme = 140 ;//未分类

        //检查更新数据
        File updatesir = new File(updateDir);
        if(!updatesir.exists()){
            log.info("目录不存在");
            return;
        }
        String[] names = updatesir.list();
        File[] files = updatesir.listFiles();
        if(names.length == 0){
            return;
        }
        log.info("待更新的演员有：");
        for (String name : names) {
            log.info(name);
        }

        //开始更新
        for (int i = 0; i < names.length; i++) {
            log.info("开始更新演员：{}",names[i]);
            File actressFile = files[i];
            //获取类型数据
            String[] types = actressFile.list();
            File[] typeFiles = actressFile.listFiles();
            if(types.length != 0){
                for (int j = 0; j < types.length; j++) {
                    log.info("开始更新{}的{}数据",names[i],types[j]);
                    File typeFile = typeFiles[j];

                    //获取视频信息
                    String[] designations = typeFile.list();
                    File[] designationFiles = typeFile.listFiles();
                    if(designations.length != 0){
                        for (int k = 0; k < designations.length; k++) {
                            log.info("正在处理：{}",designations[k]);
                            //资源信息入库
                            Resource resource = new Resource();
                            File designationFile = designationFiles[k];
                            String[] fileNames = designationFile.list();
                            File[] relFiles = designationFile.listFiles();
                            //视频名称，获取标题和视频类型
                            StringBuffer resourcename = new StringBuffer();
                            String title = null;
                            String resourcetype = null;
                            Long size = 0L;
                            for(int m = 0; m < fileNames.length;m++){
                                String fileName = fileNames[m];
                                if(fileName.toLowerCase().endsWith("mp4") || fileName.toLowerCase().endsWith("mkv") || fileName.toLowerCase().endsWith("wmv") ||
                                        fileName.toLowerCase().endsWith("avi") || fileName.toLowerCase().endsWith("rmvb") || fileName.toLowerCase().endsWith("m2ts") || fileName.toLowerCase().endsWith("MOV")
                                        || fileName.toLowerCase().endsWith("md0") || fileName.toLowerCase().endsWith("mdf")){
                                    resourcename.append(",").append(fileName);
                                    title = fileName.substring(0,fileName.lastIndexOf("."));
                                    resourcetype = fileName.substring(fileName.lastIndexOf(".")+1);
                                    size += relFiles[m].length();
                                }
                            }
                            //计算文件大小，GB
                            size = size * 100 / 1024 / 1024 / 1024;
                            Double resourceSize = Double.valueOf(String.valueOf((size / 100)) + "." + String.valueOf((size % 100)));

                            //获取番号
                            String designation = designations[k];
                            //演员名称
                            String actresses = names[i];
                            //获取演员id
                            ActorQueryCondition actorQueryCondition = new ActorQueryCondition();
                            actorQueryCondition.setName(actresses);
                            Long actressesid = actorCustomMapper.getActorsBySelective(actorQueryCondition).get(0).getId();
                            resource.setTitle(title);
                            resource.setDesignation(designation);
                            Integer resourceType = null;
                            if(!StringUtils.isBlank(resourcetype)){
                                List<DictinfoDto> dicts = dictCustomMapper.getDictsByName("视频文件格式");
                                for(DictinfoDto dict: dicts){
                                    if(resourcetype.equalsIgnoreCase(dict.getName())){
                                        resourceType = Integer.parseInt(dict.getId() + "");
                                    }
                                }
                            }
                            resource.setVideotype(resourceType);
                            resource.setVideoname(resourcename.length() == 0 ? null : resourcename.substring(1));
                            resource.setPoster("poster.jpg");
                            resource.setPosterFull("poster-full.jpg");
                            resource.setType(types[j].equals("视频") ? 0 : 1);
                            resource.setStatus(1);
                            resource.setTheme(140);
                            resource.setMark(mark);
                            resource.setTheme(theme);
                            Integer countryId = null;
                            List<DictinfoDto> dicts = dictCustomMapper.getDictsByName("国籍");
                            for(DictinfoDto dict: dicts){
                                if(country.equalsIgnoreCase(dict.getName())){
                                    countryId = Integer.parseInt(dict.getId() + "");
                                }
                            }
                            resource.setCountry(countryId);
                            resource.setSize(resourceSize);
                            resource.setResourcedir(basePath +"/" + actresses + "/" + designation);
                            resource.setCteateTime(new Date());
                            int rs = resourceMapper.insertSelective(resource);
                            RealtionActorResource realtionActorResource = new RealtionActorResource();
                            realtionActorResource.setActorId(actressesid);
                            realtionActorResource.setResourceId(resource.getId());
                            realtionActorResourceMapper.insert(realtionActorResource);
                            count += rs;
                        }
                    }
                }
            }

        }
        System.out.println("更新完毕:" + count + "条记录");
    }

}
