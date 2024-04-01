package com.campus.mapper;

import com.dto.AdminBossPageDto;
import com.entity.Boss;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AdminBossMapper {
    /**
     * 管理员分页查询所有老板信息
     * @param adminBossPageDto
     * @return
     */
    Page<Boss> pageQuery(AdminBossPageDto adminBossPageDto);

    /**
     * 管理员根据名称模糊匹配查询用户信息
     * @param nickname
     * @return
     */
    @Select("select * from boss where nickname like concat('%',#{nickname},'%')")
    ArrayList<Boss> getByName(String nickname);

    /**
     * 管理员根据id查询老板具体信息
     * @param id
     * @return
     */
    @Select("select * from boss where id =#{id}")
    Boss getById(int id);

    /**
     * 管理员修改老板信息
     * @param boss
     */
    void update(Boss boss);

    /**
     * 管理员根据id删除老板信息
     * @param id
     */
    @Update("update boss set status =0 where id= #{id}")
    void deleteById(int id);

    /**
     * 管理员删除已注销老板信息
     */
    @Delete("delete from boss where status = 2")
    void deregister();

    /**
     * 管理员添加老板
     * @param boss
     */
    @Insert(" insert into boss (nickname, account, password, gender, age, telephone, status, wallet,create_user,create_time,update_user,update_time)\n" +
            "        values (#{nickname},#{account},#{password},#{gender},#{age},#{telephone},#{status},#{wallet},#{createUser},#{createTime},#{updateUser},#{updateTime})")
    void save(Boss boss);

}
