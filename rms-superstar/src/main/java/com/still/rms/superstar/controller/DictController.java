package com.still.rms.superstar.controller;

import com.still.rms.common.api.CommonResponse;
import com.still.rms.superstar.service.DictService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author FishAndFlower
 * @Description 数据字典Controller
 * @Date 2020/8/14 14:23
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/superstar/dict")
@Api(value = "数据字典", description = "数据字典API")
public class DictController {

    @Autowired
    private DictService dictService;

    @GetMapping("info")
    public CommonResponse getDictInfoByName(String name){
        return CommonResponse.success(dictService.getDictsByName(name));
    }
}
