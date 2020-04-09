package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 微信账号相关配置
 * @author: zr
 * @date: 2020/4/6 23:06
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     *公众平台AppId
     */
    private String mpAppId;

    /**
     *公众平台AppSecret
     */
    private String mpAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书
     */
    private String keyPath;

    /**
     *微信支付异步通知URL
     */
    private String notifyUrl;

    /**
     *开放平台appId
     */
    private String openAppId;

    /**
     *开放平台AppSecret
     */
    private String openAppSecret;
}
