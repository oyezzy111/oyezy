package com.entity;

import lombok.Data;

@Data
public class User {

    private int id;
    private String nickname;
    private String account;
    private String password;
    private String gender;
    private int age;
    private String telephone;
    private int status;
    private double wallet;
    private double riderWallet;
    private int createUser;
    private String createTime;
    private int updateUser;
    private String updateTime;
}
