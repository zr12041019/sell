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
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-hover table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>类目ID</th>
                            <th>类目名称</th>
                            <th>type</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productCategoryList as productCategory>
                            <tr>
                                <td>${productCategory.categoryId}</td>
                                <td>${productCategory.categoryName}</td>
                                <td>${productCategory.categoryType}</td>
                                <td>${productCategory.createTime}</td>
                                <td>${productCategory.updateTime}</td>
                                <td>
                                    <a href="/sell/seller/category/index?categoryId=${productCategory.categoryId}">修改</a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>