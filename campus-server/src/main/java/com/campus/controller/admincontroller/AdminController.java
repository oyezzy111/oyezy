package com.campus.controller.admincontroller;

import com.campus.service.impl.AdminServiceImpl;
import com.constant.JwtClaimsConstant;
import com.constant.OrderTypeConstant;
import com.dto.AdminLoginDto;
import com.dto.AdminUpdateDto;
import com.entity.Admin;
import com.properties.JwtProperties;
import com.result.Result;
import com.utils.JwtUtil;
import com.vo.AdminLoginVo;
import com.vo.AdminQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/admin")
@Api(tags = "管理员管理自我接口")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 管理员登录
     * @param adminLoginDto
     * @return
     */
    @GetMapping("/login")
    @ApiOperation("管理员登录接口")
    public Result<AdminLoginVo> adminLogin(@RequestBody AdminLoginDto adminLoginDto) {
        Admin admin = adminServiceImpl.login(adminLoginDto);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, admin.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        AdminLoginVo adminLoginVo = AdminLoginVo.builder()
                .id(admin.getId())
                .account(admin.getAccount())
                .token(token)
                .build();

        return Result.success(adminLoginVo);
    }

    /**
     * 管理员查看个人信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("管理员查看个人信息")
    public Result<Admin> Query(@PathVariable int id) {
        Admin admin = adminServiceImpl.query(id);
        return Result.success(admin);
    }

    /**
     * 管理员修改个人信息
     * @param adminUpdateDto
     * @return
     */
    @ApiOperation("管理员修改个人信息")
    @PutMapping
    public Result update(@RequestBody AdminUpdateDto adminUpdateDto) {
        adminServiceImpl.update(adminUpdateDto);
        return Result.success();
    }

    /**
     * 管理员查询网站月总流水
     * @param year
     * @param month
     * @return
     */
    @GetMapping("/month/{year}/{month}")
    @ApiOperation("管理员查询网站月总流水")
    public Result<AdminQueryVo> sumMonth(@PathVariable int year, @PathVariable int month) {
        AdminQueryVo adminQueryVo = adminServiceImpl.monthSum(year, month);
        return Result.success(adminQueryVo);
    }


    /**
     * 管理员查询网站年流水
     * @param year
     * @return
     */
    @GetMapping("/year/{year}")
    @ApiOperation("管理员查询网站年总流水")
    public Result<ArrayList<AdminQueryVo>> yearSumQuery(@PathVariable int year) {
        ArrayList<AdminQueryVo> queryList = adminServiceImpl.yearSumQuery(year);
        return Result.success(queryList);
    }
}
