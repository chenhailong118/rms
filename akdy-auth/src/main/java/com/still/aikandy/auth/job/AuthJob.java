package com.still.aikandy.auth.job;

import com.still.aikandy.auth.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author Lee
 * @Description 定时任务
 * @Date 2020/6/26 20:56
 * @Version 1.0
 */
@Slf4j
@Component
public class AuthJob {
    private final RoleService roleService;

    public AuthJob(RoleService roleService) {
        this.roleService = roleService;
    }

}
