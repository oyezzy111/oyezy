package com.campus.service;

import com.vo.AdminQueryVo;

import java.util.ArrayList;

public interface AdminWebService {
    /**
     * 查询超市月流水
     *  @param year
     * @param month
     * @return
     */
    AdminQueryVo monthSum(int year, int month);



    /**
     * 查询跑腿年流水
     * @param year
     * @return
     */
    ArrayList<AdminQueryVo> yearSumQuery(int year);

}
