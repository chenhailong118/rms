package com.still.rms.auth.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author FishAndFlower
 * @Description 头像Service
 * @Date 2020/11/3 12:36
 * @Version 1.0
 */
public interface IconService {
    /**
     * 用户头像上传
     * @param icon 用户头像
     * @return
     */
    String iconUpload(MultipartFile icon);
}
