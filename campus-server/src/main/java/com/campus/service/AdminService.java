package com.campus.service;

import com.dto.AdminLoginDto;
import com.dto.AdminUpdateDto;
import com.entity.Admin;
import com.vo.AdminQueryVo;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;

public interface AdminService {
    /**
     * 管理员修改个人信息表
     * @param adminUpdateDto
     */
    void update(AdminUpdateDto adminUpdateDto);

    /**
     * 管理员登陆
     * @param adminLoginDto
     * @return
     * @throws AccountNotFoundException
     */
    Admin login(AdminLoginDto adminLoginDto) throws AccountNotFoundException;

    /**
     * 管理员查询个人信息
     * @return
     */
    Admin query(int id);
}
