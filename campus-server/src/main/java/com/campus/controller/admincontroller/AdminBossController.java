package com.campus.controller.admincontroller;

import com.campus.service.impl.AdminBossServiceImpl;
import com.dto.*;
import com.entity.Boss;
import com.entity.User;
import com.result.PageResult;
import com.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin/boss")
@Slf4j
@Api(tags = "管理员老板接口")
public class AdminBossController {
    private final AdminBossServiceImpl adminBossServiceImpl;

    public AdminBossController(AdminBossServiceImpl adminBossServiceImpl) {
        this.adminBossServiceImpl = adminBossServiceImpl;
    }

    /**
     * 管理员分页查询所有老板信息
     * @param adminBossPageDto
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("管理员分页查询所有老板信息")
    public Result<PageResult> getPage(AdminBossPageDto adminBossPageDto){
        PageResult pageResult = adminBossServiceImpl.pageQuery(adminBossPageDto);
        return Result.success(pageResult);
    }

    /**
     *管理员根据id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("管理员根据id查询老板信息")
    public Result<Boss> getById(@PathVariable int id){
        Boss boss= adminBossServiceImpl.getById(id);
        return Result.success(boss);
    }

    /**
     * 管理员修改老板信息
     * @param adminBossUpdateDto
     * @return
     */
    @PutMapping
    @ApiOperation("管理员修改老板信息")
    public Result updateUser(@RequestBody AdminBossUpdateDto adminBossUpdateDto){
        adminBossServiceImpl.update(adminBossUpdateDto);
        return Result.success();
    }

    /**
     * 管理员删除老板信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("管理员删除老板信息")
    public Result deleteById(@PathVariable int id){
       adminBossServiceImpl.deleteById(id);
        return Result.success();
    }

    /**
     * 管理员删除已注销老板信息
     */
//    @DeleteMapping
//    @ApiOperation("管理员删除已注销老板信息")
//    public Result deregister(){
//        adminBossServiceImpl.deregister();
//        return Result.success();
//    }

    /**
     * 管理员新增老板信息
     * @param adminBossSaveDto
     * @return
     */
    @PostMapping
    @ApiOperation("管理员新增老板信息")
    public Result save(@RequestBody AdminBossSaveDto adminBossSaveDto){
        adminBossServiceImpl.save(adminBossSaveDto);
        return Result.success();
    }
}
