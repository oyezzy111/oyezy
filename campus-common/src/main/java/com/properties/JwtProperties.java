package com.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "campus.jwt")
@Data
public class JwtProperties {

    /**
     * 用户登录生成jwt令牌相关配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

//    /**
//     * 用户端微信用户生成jwt令牌相关配置
//     */
//    private String userSecretKey;
//    private long userTtl;
//    private String userTokenName;

}
