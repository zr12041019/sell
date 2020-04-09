package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 16:30
 */
@ConfigurationProperties(prefix = "projectUrl")
@Data
@Component
public class ProjectUrlConfig {

    /**
     *微信公众平台授权url
     */
    private String wechatMpAuthorize;

    /**
     * 微信开放平台授权url
     */
    private String wechatOpenAuthorize;

    /**
     * 点餐系统
     */
    private String sell;
}
