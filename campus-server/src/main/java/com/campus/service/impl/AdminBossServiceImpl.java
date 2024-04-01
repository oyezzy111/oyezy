package com.campus.service.impl;

import com.campus.mapper.AdminBossMapper;
import com.campus.service.AdminBossService;
import com.constant.PasswordConstant;
import com.dto.AdminBossPageDto;
import com.dto.AdminBossSaveDto;
import com.dto.AdminBossUpdateDto;
import com.dto.AdminUserSaveDto;
import com.entity.Boss;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminBossServiceImpl implements AdminBossService {

    @Autowired
    AdminBossMapper adminBossMapper;

    /**
     * 管理员分页查询所有老板信息
     * @param adminBossPageDto
     * @return
     */
    @Override
    public PageResult pageQuery(AdminBossPageDto adminBossPageDto) {
        PageHelper.startPage(adminBossPageDto.getPage(),adminBossPageDto.getPageSize());
        Page<Boss> page = adminBossMapper.pageQuery(adminBossPageDto);
        long total = page.getTotal();
        List<Boss> result = page.getResult();
        return new PageResult(total,result);
    }

    /**
     * 管理员根据昵称模糊匹配老板信息
     * @param nickname
     * @return
     */
    @Override
    public ArrayList<Boss> getByName(String nickname) {
        ArrayList<Boss> list = adminBossMapper.getByName(nickname);
        return list;
    }

    /**
     * 管理员根据id查询老板信息
     * @param id
     * @return
     */
    @Override
    public Boss getById(int id) {
        Boss boss = adminBossMapper.getById(id);
        boss.setPassword("******");
        return boss;
    }

    /**
     * 管理员修改老板信息
     * @param adminBossUpdateDto
     */
    @Override
    public void update(AdminBossUpdateDto adminBossUpdateDto) {
        Boss boss = new Boss();
        BeanUtils.copyProperties(adminBossUpdateDto,boss);
//        //TODO 设置修改时间
        boss.setUpdateTime("" + LocalDateTime.now());
//        //TODO 设置管理员修改用户的Id
        boss.setUpdateUser(1);
        adminBossMapper.update(boss);
    }

    /**
     * 管理员根据Id删除老板信息
     * @param id
     */
    @Override
    public void deleteById(int id) {
        adminBossMapper.deleteById(id);
    }

    @Override
    public void deregister() {
        adminBossMapper.deregister();
    }

    /**
     * 管理员添加老板
     * @param adminBossSaveDto
     */
    @Override
    public void save(AdminBossSaveDto adminBossSaveDto) {
        Boss boss = new Boss();
        BeanUtils.copyProperties(adminBossSaveDto,boss);
        boss.setPassword(PasswordConstant.PASSWORD);
        //TODO 管理员添加用户时间
//        boss.setCreateTime(" "+LocalDateTime.now());
//        boss.setCreateUser(1);
//        boss.setUpdateUser(1);
//        boss.setUpdateTime(" "+LocalDateTime.now());
        adminBossMapper.save(boss);
    }
}
