package com.campus.mapper;

import com.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminMapper {

    /**
     * 管理员修改个人信息
     * @param admin
     */
    void update(Admin admin);

     /**
      * 管理员登录
      * @param account
      * @param password
      * @return
      */
      @Select("select * from admin where account = #{account} and password = #{password}")
      Admin login(String account, String password);

      /**
       * 管理员查询个人信息
       * @return
       */
       @Select("select * from admin where id= #{id}")
       Admin query(int id);

    /**
     * 0已接单 1骑手接单 2已送达 3已签收
     * 查询超市月流水
     * @param year
     * @param month
     * @return
     */
    @Select("select sum(money) from shopping_order where status = 3 and month(create_time)=#{month} and year(create_time)=#{year}")
    Double monthShopQuery(int year,int month);

    /**
     * 查询跑腿月流水
     * @param year
     * @param month
     * @return
     */
    @Select("select sum(money) from takeaway_order where status = 3 and month(create_time)=#{month} and year(create_time)=#{year}")
    Double  monthTakeawayQuery(int year,int month);

    /**
     * 查询超市年流水
     * @param year
     * @return
     */
    @Select("select sum(money) from shopping_order where status = 3 and year(create_time)=#{year}")
    Double  yearShopQuery(int year);

    /**
     * 查询跑腿年流水
     * @param year
     * @return
     */
    @Select("select sum(money) from takeaway_order where status = 3 and year(create_time)=#{year}")
    Double  yearTakeawayQuery(int year);

    /**
     * 查询月使用超市用户人数
     * @param year
     * @param month
     * @return
     */
    @Select("select  count(distinct user_id) from shopping_order where status = 3 and month(create_time)=#{month} and year(create_time)=#{year}")
    Integer monthShopUser(int year,int month);

    /**
     * 查询年使用超市用户人数
     * @param year
     * @return
     */
    @Select("select count(distinct user_id) from shopping_order where status = 3 and year(create_time)=#{year}")
    Integer yearShopUser(int year);

    /**
     * 查询月使用跑腿用户人数
     * @param year
     * @param month
     * @return
     */
    @Select("select  count( distinct user_id) from takeaway_order where status = 3 and month(create_time)=#{month} and year(create_time)=#{year}")
    Integer monthTakeawayUser(int year,int month);

    /**
     * 查询年使用跑腿用户人数
     * @param year
     * @return
     */
    @Select("select  count(distinct user_id) from takeaway_order where status = 3  and year(create_time)=#{year}")
    Integer yearTakeawayUser(int year);

    /**
     * 查询超市月完成订单数
     * @param year
     * @param month
     * @return
     */
    @Select("select count(id) from shopping_order where status = 3 and month(create_time)=#{month} and year(create_time)=#{year}")
    Integer monthShopOrder(int year,int month);

    /**
     * 查询超市年完成订单数
     * @param year
     * @return
     */
    @Select("select  count(id) from shopping_order where status = 3  and year(create_time)=#{year}")
    Integer yearShopOrder(int year);

    /**
     * 查询跑腿月完成订单数
     * @param year
     * @param month
     * @return
     */
    @Select("select  count(id) from takeaway_order where status = 3  and month(create_time)=#{month} and year(create_time)=#{year}")
    Integer monthTakeawayOrder(int year,int month);

   /**
    * 查询跑腿年完成订单数
    * @param year
    * @return
    */
    @Select("select count(id) from takeaway_order where status = 3  and year(create_time)=#{year}")
    Integer yearTakeawayOrder(int year);
}
