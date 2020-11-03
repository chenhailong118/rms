package com.still.rms.auth.service;

import com.still.rms.auth.component.AuthProperties;
import com.still.rms.common.api.ApiException;
import com.still.rms.common.api.ResultCode;
import com.still.rms.common.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author FishAndFlower
 * @Description 头像Service
 * @Date 2020/11/3 12:37
 * @Version 1.0
 */
@Service
public class IconServiceImpl implements IconService{

    private final AuthProperties authProperties;

    public IconServiceImpl(AuthProperties authProperties) {
        this.authProperties = authProperties;
    }

    /**
     * 用户头像上传
     * @param icon 用户头像
     * @return
     */
    @Override
    public String iconUpload(MultipartFile icon) {
        try {
            return authProperties.getIconPath() + FileUtil.saveToLocal(icon,authProperties.getBasePath() + authProperties.getIconPath());
        } catch (IOException e) {
            throw new ApiException(ResultCode.ICON_PARSE_WRONG);
        }
    }
}
