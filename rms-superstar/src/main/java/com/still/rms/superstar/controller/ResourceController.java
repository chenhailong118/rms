package com.still.rms.superstar.controller;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.common.api.ResultCode;
import com.still.rms.common.dto.ActorIdAndName;
import com.still.rms.common.dto.ResourceDto;
import com.still.rms.common.dto.ResourceTagDto;
import com.still.rms.common.querycondition.ResourceQueryCondition;
import com.still.rms.mbg.model.Resource;
import com.still.rms.superstar.component.SuperstarProperties;
import com.still.rms.superstar.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author FishAndFlower
 * @Description 资源
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/superstar/resource")
@Api(value = "资源", description = "资源API")
@Validated
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private SuperstarProperties superstarProperties;

    /**
     * 根据条件查询资源信息
     * @param resourceQueryCondition 资源查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @ApiOperation(value = "获取资源列表", notes = "根据条件查询资源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "Integer")
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CommonResponse<PageInfo<List<Resource>>> queryResources(
            ResourceQueryCondition resourceQueryCondition,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize){
        return CommonResponse.success(resourceService.queryResources(resourceQueryCondition, pageNum, pageSize));
    }

    /**
     * 添加资源信息
     * @param resource 资源信息
     * @return
     */
    @ApiOperation(value = "添加资源信息",notes = "添加资源信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResponse<Integer> addResource(@RequestBody ResourceDto resource){
        Integer count = resourceService.addResource(resource);
        return CommonResponse.success(count);
    }

    /**
     * 修改资源信息
     * @param id 资源id
     * @param resourceDto 资源信息
     * @return
     */
    @ApiOperation(value = "修改资源信息",notes = "修改资源信息")
    @ApiImplicitParam(name = "id", value = "资源id")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public CommonResponse<Integer> editResource(@NotNull @PathVariable(value = "id") Long id, @Validated @RequestBody ResourceDto resourceDto){
        Integer count = resourceService.updateResource(id,resourceDto);
        return CommonResponse.success(count);
    }

    /**
     * 删除资源信息
     * @param id 资源id
     * @return
     */
    @ApiOperation(value = "删除资源信息",notes = "删除资源信息")
    @ApiImplicitParam(name = "id", value = "资源id")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public CommonResponse<Integer> deleteResource(@PathVariable(value = "id") Long id){
        Integer count = resourceService.deleteResource(id);
        return CommonResponse.success(count);
    }

    /**
     * 根据资源ID查询演员列表
     * @param id 资源id
     * @return
     */
    @ApiOperation(value = "根据资源ID查询演员列表", notes = "根据资源ID查询演员列表")
    @ApiImplicitParam(name = "id", value = "资源id", dataType = "Integer")
    @RequestMapping(value = "actors/{id}", method = RequestMethod.GET)
    public CommonResponse<List<ActorIdAndName>> queryActorIds(@PathVariable("id") Long id){
        return CommonResponse.success(resourceService.queryActors(id));
    }

    /**
     * 根据文件路径查询图片列表
     * @param dir 资源路径
     * @return
     */
    @ApiOperation(value = "获取资源图片列表", notes = "根据资源路径查询资源图片列表")
    @ApiImplicitParam(name = "dir", value = "文件存储路径", dataType = "String")
    @RequestMapping(value = "image", method = RequestMethod.GET)
    public CommonResponse<List<String>> queryImages(String dir){
        dir = dir.substring(1);
        String relativePath = dir.substring(dir.indexOf("/"));
        List<String> images = new ArrayList();
        superstarProperties.getBasePath().stream().forEach(basePath ->{
            String imageDir = basePath + relativePath + "/image";
            File[] imageFile = new File(imageDir).listFiles();
            if (imageFile != null) {
                for (File file : imageFile) {
                    if (file.isFile() && file.getName().endsWith("jpg")) {
                        images.add(file.getName());
                    }
                }
            }
        });
        return CommonResponse.success(images);
    }

    /**
     * 根据文件路径查询GIF图片列表
     * @param dir 资源路径
     * @return
     */
    @ApiOperation(value = "获取资源GIF图片列表", notes = "根据资源路径查询资源GIF图片列表")
    @ApiImplicitParam(name = "dir", value = "文件存储路径", dataType = "String")
    @RequestMapping(value = "gif", method = RequestMethod.GET)
    public CommonResponse<List<String>> queryGifs(String dir){
        dir = dir.substring(1);
        String relativePath = dir.substring(dir.indexOf("/"));
        List<String> gifs = new ArrayList();
        superstarProperties.getBasePath().stream().forEach(basePath ->{
            String imageDir = basePath + relativePath + "/gif";
            File[] imageFile = new File(imageDir).listFiles();
            if (imageFile != null) {
                for (File file : imageFile) {
                    if (file.isFile() && file.getName().endsWith("gif")) {
                        gifs.add(file.getName());
                    }
                }
            }
        });
        return CommonResponse.success(gifs);
    }

    @ApiOperation("给资源添加标签")
    @PostMapping("/allocTag")
    public CommonResponse allocTag(ResourceTagDto resourceTagDto){
        int count = resourceService.updateResourceTags(resourceTagDto.getResourceId(), resourceTagDto.getTagIds());
        if (count >= 0) {
            return CommonResponse.success(count);
        }
        return CommonResponse.error(ResultCode.UNKNOWN_ERROR);
    }

    /**
     * 打开本地文件夹
     * @param dir
     * @return
     */
    @GetMapping("/opendir")
    public CommonResponse openLocalDir(String dir) {
        dir = dir.substring(1);
        String relativePath = dir.substring(dir.indexOf("/"));
        superstarProperties.getBasePath().stream().forEach(basePath -> {
            try {
                String fullDir = basePath + relativePath;
                String cmd = "cmd /c start explorer " + fullDir.replace("/", "\\");
                File[] files = new File(fullDir).listFiles();
                if (files != null) {
                    Runtime.getRuntime().exec(cmd);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return CommonResponse.success();
    }
}
