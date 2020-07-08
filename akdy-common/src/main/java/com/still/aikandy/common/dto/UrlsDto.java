package com.still.aikandy.common.dto;

import lombok.Data;

/**
 * @Author Lee
 * @Description TODO
 * @Date 2020/6/28 17:54
 * @Version 1.0
 */
@Data
public class UrlsDto {
    //上传头像地址
    private String postIconUrl;
    //获取图像的nginx服务器地址
    private String imageServer;
}
