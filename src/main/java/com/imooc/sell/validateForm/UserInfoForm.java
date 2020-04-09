package com.imooc.sell.validateForm;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 21:53
 */
@Data
public class UserInfoForm {
    /**
     * ID
     */
    private String sellerId;

    /**
     *用户姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
