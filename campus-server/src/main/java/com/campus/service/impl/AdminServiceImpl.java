package com.campus.service.impl;

import com.campus.mapper.AdminMapper;
import com.campus.service.AdminService;
import com.constant.MessageConstant;

import com.constant.OrderTypeConstant;
import com.dto.AdminLoginDto;
import com.dto.AdminUpdateDto;
import com.entity.Admin;
import com.exception.AccountNotFoundException;
import com.exception.PasswordErrorException;
import com.vo.AdminQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员修改个人信息
     * @param adminUpdateDto
     */
    @Override
    public void update(AdminUpdateDto adminUpdateDto) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminUpdateDto,admin);
        admin.setUpdateTime(""+LocalDateTime.now());
        //TODO 获取修改人ID
        admin.setUpdateUser(1);
        adminMapper.update(admin);
    }

    /**
     * 查询网站月流水
     * @param year
     * @param month
     * @return
     */
    @Override
    public AdminQueryVo monthSum(int year,int month) {
        AdminQueryVo adminQueryVo = new AdminQueryVo();
        adminQueryVo.setMonth(month);
        adminQueryVo.setType1(OrderTypeConstant.SHOP_ORDER);
        Double shopMoney = adminMapper.monthShopQuery(year, month);
        if(shopMoney==null){
            adminQueryVo.setShopMoney(0.0);
            shopMoney=0.0;
        }else{
            adminQueryVo.setShopMoney(shopMoney);
        }
        adminQueryVo.setType2(OrderTypeConstant.TAKEAWAY_ORDER);
        Double takeawayMoney = adminMapper.monthTakeawayQuery(year, month);
        if(takeawayMoney==null){
            adminQueryVo.setTakeawayMoney(0.0);
            takeawayMoney=0.0;
        }else{
            adminQueryVo.setTakeawayMoney(shopMoney);
        }
        Double sumMoney = shopMoney+takeawayMoney;
        adminQueryVo.setSum(sumMoney);
        return adminQueryVo;
    }

    /**
     * 查询网站年流水
     * @param year
     * @return
     */
    @Override
    public ArrayList<AdminQueryVo> yearSumQuery(int year) {
        ArrayList<AdminQueryVo> queryList = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            AdminQueryVo adminQueryVo = new AdminQueryVo();
            adminQueryVo.setType1(OrderTypeConstant.SHOP_ORDER);
            adminQueryVo.setType2(OrderTypeConstant.TAKEAWAY_ORDER);
            adminQueryVo.setMonth(i);
            Double shopMoney = adminMapper.monthShopQuery(year, i);
            if(shopMoney==null){
                adminQueryVo.setShopMoney(0.0);
                shopMoney=0.0;
            }else{
                adminQueryVo.setShopMoney(shopMoney);
            }
            Double takeawayMoney = adminMapper.monthTakeawayQuery(year, i);
            if(takeawayMoney==null){
                adminQueryVo.setTakeawayMoney(0.0);
                takeawayMoney=0.0;
            }else{
                adminQueryVo.setTakeawayMoney(shopMoney);
            }
            Double sumMoney = shopMoney+takeawayMoney;
            adminQueryVo.setSum(sumMoney);
            queryList.add(adminQueryVo);
        }
        AdminQueryVo adminQueryVo = new AdminQueryVo();
        adminQueryVo.setMonth(13);
        adminQueryVo.setType1(OrderTypeConstant.SHOP_ORDER);
        adminQueryVo.setType2(OrderTypeConstant.TAKEAWAY_ORDER);
        Double yearShopQuery = adminMapper.yearShopQuery(year);
        if(yearShopQuery==null){
            adminQueryVo.setShopMoney(0.0);
            yearShopQuery=0.0;
        }else {
            adminQueryVo.setShopMoney(yearShopQuery);
        }
        Double yearTakeawayMoney = adminMapper.yearTakeawayQuery(year);
        if(yearTakeawayMoney==null){
            adminQueryVo.setTakeawayMoney(0.0);
            yearTakeawayMoney=0.0;
        }else{
            adminQueryVo.setTakeawayMoney(yearTakeawayMoney);
        }
        Double sumMoney = yearTakeawayMoney+yearShopQuery;
        adminQueryVo.setSum(sumMoney);
        queryList.add(adminQueryVo);
        return queryList;
    }

    /**
     * 管理员登录
     * @param adminLoginDto
     * @return
     * @throws AccountNotFoundException
     */
    @Override
    public Admin login(AdminLoginDto adminLoginDto) throws AccountNotFoundException {
        String account  = adminLoginDto.getAccount();
        String password = adminLoginDto.getPassword();
        //1、根据用户名查询数据库中的数据
        Admin admin = adminMapper.login(adminLoginDto.getAccount(), adminLoginDto.getPassword());

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (admin == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_EXIST);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(admin.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //3、返回实体对象
        return  admin;
    }

    /**
     * 管理员查询个人信息
     * @return
     */
    @Override
    public Admin query(int id) {
        Admin admin =adminMapper.query(id);
        return admin;
    }

}
