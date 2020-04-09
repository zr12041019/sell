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
                <form role="form" method="post" action="/sell/seller/category/save">
                    <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}">
                    <div class="form-group">
                        <label for="categoryName">类目名称</label>
                        <input name="categoryName" type="text" class="form-control" id="categoryName" value="${(productCategory.categoryName)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="categoryType">type</label>
                        <input name="categoryType" type="number" class="form-control" id="categoryType" value="${(productCategory.categoryType)!''}" />
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