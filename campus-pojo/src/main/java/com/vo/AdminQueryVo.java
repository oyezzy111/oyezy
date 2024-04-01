package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("管理员查询总流水返回数据模型")
public class AdminQueryVo {
    @ApiModelProperty("月份")
    int month;
    @ApiModelProperty("超市")
    String type1;
    @ApiModelProperty("超市流水")
    Double shopMoney=0.0;
    @ApiModelProperty("跑腿")
    String type2;
    @ApiModelProperty("跑腿流水")
    Double takeawayMoney=0.0;
    @ApiModelProperty("总流水")
    Double sum=0.0;
}
