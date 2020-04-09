package com.imooc.sell.controller;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.ProductCategoryService;
import com.imooc.sell.validateForm.CategoryForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: zr
 * @date: 2020/4/9 0:27
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 商品类目列表
     * @param map map
     * @return ModelAndView
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("category/list",map);
    }

    /**
     * 展示
     * @param categoryId categoryId
     * @param map Map<String,Object>
     * @return ModelAndView
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){

        if(null != categoryId){
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
        }
        return new ModelAndView("category/index",map);
    }

    /**
     * 保存或者修改
     * @param categoryForm categoryForm
     * @param bindingResult bindingResult
     * @param map Map<String,Object>
     * @return ModelAndView
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        ProductCategory productCategory = new ProductCategory();
        try {
            if (null != categoryForm.getCategoryId()) {
                productCategory = productCategoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            productCategoryService.save(productCategory);
        }catch(SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}
