package com.still.aikandy.common.dto;

import lombok.Data;


/**
 * @Author FishAndFlower
 * @Description urls传输对象
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class UrlsDto {
    //上传头像地址
    private String postIconUrl;
    //获取图像的nginx服务器地址
    private String imageServer;
}
