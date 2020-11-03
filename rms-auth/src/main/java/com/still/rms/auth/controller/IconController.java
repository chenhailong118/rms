package com.still.rms.auth.controller;

import com.still.rms.auth.service.IconService;
import com.still.rms.common.api.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author FishAndFlower
 * @Description 头像Controller
 * @Date 2020/11/3 12:35
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/auth/icon")
@Api(value = "头像", description = "头像API")
@Validated
public class IconController {
    private final IconService iconService;

    public IconController(IconService iconService) {
        this.iconService = iconService;
    }

    /**
     * 用户头像上传
     * @param icon
     * @return
     */
    @ApiOperation(value = "用户头像上传",notes = "创建用户前，先提交头像信息")
    @ApiParam(name = "file", value = "用户头像",required = true)
    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResponse<String> iconUpload(@RequestParam("file") MultipartFile icon){
        return CommonResponse.success(iconService.iconUpload(icon));
    }
}
