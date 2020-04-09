package com.imooc.sell.validateForm;

import lombok.Data;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 13:33
 */
@Data
public class CategoryForm {
    /**
     * ID
     */
    private Integer categoryId;

    /* 类目名称 */
    private String categoryName;

    /* 类目编号 */
    private Integer categoryType;
}
