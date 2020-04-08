<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

    <#--    左边栏-->

    <#include "../common/nav.ftl">
    <#--    主要类容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="col-md-4 column">
            </div>
            <div class="col-md-4 column">
                <form role="form" method="post" action="/sell/seller/product/save">
                    <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                    <div class="form-group">
                        <label for="productName">商品名称</label>
                        <input name="productName" type="text" class="form-control" id="productName" value="${(productInfo.productName)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="productPrice">商品价格</label>
                        <input name="productPrice" type="text" class="form-control" id="productPrice" value="${(productInfo.productPrice)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="productStock">商品库存</label>
                        <input name="productStock" type="number" class="form-control" id="productStock" value="${(productInfo.productStock)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="productDescription">商品描述</label>
                        <input name="productDescription" type="text" class="form-control" id="productDescription" value="${(productInfo.productDescription)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="productIcon">商品图片</label>
                        <img width="100" height="100" src="${(productInfo.productIcon)!''}" alt="">
                        <input name="productIcon" type="text" class="form-control" id="productIcon" value="${(productInfo.productIcon)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="categoryType">商品类目</label>
                        <select name="categoryType" id="categoryType" class="form-control">
                            <#list productCategoryList as productCategory>
                                <option value="${productCategory.categoryType}"
                                            <#if (productInfo.categoryType)?? && productCategory.categoryType == productInfo.categoryType>
                                                selected
                                            </#if>
                                            >
                                    ${productCategory.categoryName}
                                </option>
                            </#list>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
            <div class="col-md-4 column">
            </div>
        </div>
    </div>
</div>
</body>
</html>