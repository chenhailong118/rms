package com.still.rms.superstar.controller;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.common.querycondition.LogQueryCondition;
import com.still.rms.mbg.model.Log;
import com.still.rms.superstar.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description Log Controller
 * @Date 2020/8/30 11:33
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/superstar/log")
@Api(value = "日志", description = "日志API")
public class LogController {
    @Autowired
    private LogService logService;
    
    /**
     * 根据条件查询日志信息
     * @param logQueryCondition 日志查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @ApiOperation(value = "获取日志列表", notes = "根据条件查询日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "Integer")
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CommonResponse<PageInfo<List<Log>>> queryLogs(
            LogQueryCondition logQueryCondition,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize){
        return CommonResponse.success(logService.queryLogs(logQueryCondition, pageNum, pageSize));
    }

    /**
     * 根据ID查询日志详情
     * @param id 日志ID
     * @return
     */
    @ApiOperation(value = "获取日志详情", notes = "根据ID查询日志详情")
    @ApiImplicitParam(name = "id", value = "日志ID", dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResponse<Log> queryLogById(@PathVariable(value = "id") Long id){
        return CommonResponse.success(logService.queryLogById(id));
    }
}
